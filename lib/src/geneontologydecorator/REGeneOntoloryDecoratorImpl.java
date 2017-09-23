package geneontologydecorator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;

import kbaserelationengine.GetWSFeatureTermEnrichmentProfilesOutput;
import kbaserelationengine.GetWSFeatureTermEnrichmentProfilesParams;
import kbaserelationengine.GetWSFeatureTermPairsParams;
import kbaserelationengine.KBaseRelationEngineServiceClient;
import kbaserelationengine.TermEnrichment;
import kbaserelationengine.TermEnrichmentProfile;
import kbaserelationengine.WSFeatureTermPair;
import kbkeutil.CalcOnthologyDistParams;
import kbkeutil.KbKeUtilServiceClient;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientCaller;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.UObject;
import us.kbase.common.service.UnauthorizedException;

public class REGeneOntoloryDecoratorImpl implements IGeneOntologyDecoratorImpl {

    private final URL srvWizUrl;
    private final AuthToken keAdmin;

    private static final List<String> termRelationTypes = Arrays.asList(
            "reference", "kbase", "expression", "fitness");

    private static final List<String[]> termRelationAppTypes = Arrays.asList(
            new String[] {"kbase", "KEApp7"},
            new String[] {"expression", "KEApp5"},
            new String[] {"fitness", "KEApp6"});

    public REGeneOntoloryDecoratorImpl(URL srvWizUrl, AuthToken keAdmin) {
        this.srvWizUrl = srvWizUrl;
        this.keAdmin = keAdmin;
    }
    
    private KBaseRelationEngineServiceClient getRE() throws UnauthorizedException, IOException {
        KBaseRelationEngineServiceClient ret = new KBaseRelationEngineServiceClient(srvWizUrl,
                keAdmin);
        ret.setIsInsecureHttpConnectionAllowed(true);
        ret.setServiceVersion("dev");
        return ret;
    }

    private KbKeUtilServiceClient getKEUtil() throws UnauthorizedException, IOException {
        KbKeUtilServiceClient ret = new KbKeUtilServiceClient(srvWizUrl, keAdmin);
        ret.setIsInsecureHttpConnectionAllowed(true);
        ret.setServiceVersion("dev");
        return ret;
    }

    @Override
    public Map<String, TermProfile> getTermRelations(
            GetTermRelationsParams params) throws Exception {
        String wsFeatureGuid = params.getFeatureGuid();
        List<String> keappGuids = termRelationAppTypes.stream().map(item -> item[1])
                .collect(Collectors.toList());
        GetWSFeatureTermEnrichmentProfilesOutput output = 
                getRE().getWSFeatureTermEnrichmentProfiles(
                        new GetWSFeatureTermEnrichmentProfilesParams()
                        .withWsFeatureGuid(wsFeatureGuid)
                        .withKeappGuids(keappGuids));
        Map<String, TermProfile> ret = new HashMap<>();
        Term refTerm = new Term().withTermGuid(output.getRefTermGuid())
                .withTermName(output.getRefTermName()).withPvalue(1.0).withTermPosition(0.0);
        ret.put("reference", new TermProfile().withBestTerm(refTerm)
                .withTerms(Arrays.asList(refTerm)));
        Map<String, String> appToTermRelType = termRelationAppTypes.stream().collect(
                Collectors.toMap(item -> item[1], item -> item[0]));
        Map<String, List<String>> termPairs = new HashMap<>();
        for (TermEnrichmentProfile tep : output.getProfiles()) {
            String app = tep.getKeappGuid();
            String termRelType = appToTermRelType.get(app);
            if (termRelType == null) {
                continue;
            }
            List<Term> terms = new ArrayList<>();
            for (TermEnrichment te : tep.getTerms()) {
                Term t = new Term().withPvalue(te.getPValue())
                        .withTermGuid(te.getTermGuid()).withTermName(te.getTermName());
                terms.add(t);
                if (refTerm.getTermGuid() != null && t.getTermGuid() != null) {
                    String termPairKey = refTerm.getTermGuid() + "_" + t.getTermGuid();
                    termPairs.put(termPairKey, Arrays.asList(refTerm.getTermGuid(), t.getTermGuid()));
                }
            }
            Term bestTerm = terms.stream().min(
                    (t1, t2) -> Double.compare(t1.getPvalue(), t2.getPvalue())).get();
            TermProfile outProfile = new TermProfile();
            outProfile.withBestTerm(bestTerm).withTerms(terms);
            ret.put(termRelType, outProfile);
        }
        if (termPairs.size() > 0) {
            Map<String, Double> termPairToDistance = calcWeightedOnthologyDist(termPairs);
            for (TermProfile profile : ret.values()) {
                for (Term t : profile.getTerms()) {
                    if (t.getTermGuid() != null) {
                        String termPairKey = refTerm.getTermGuid() + "_" + t.getTermGuid();
                        Double distance = termPairToDistance.get(termPairKey);
                        if (distance != null) {
                            t.setTermPosition((double)distance);
                        }
                    }
                }
            }
        }
        return ret;
    }

    private Map<String, Double> calcWeightedOnthologyDist(
            Map<String, List<String>> termPairs) throws Exception {
        JsonClientCaller caller = new JsonClientCaller(srvWizUrl, keAdmin);
        caller.setDynamic(true);
        List<Object> args = new ArrayList<Object>();
        args.add(new CalcOnthologyDistParams().withOnthologySet(termPairs));
        TypeReference<List<Map>> retType = new TypeReference<List<Map>>() {};
        List<Map> res = caller.jsonrpcCall("kb_ke_util.calc_weighted_onthology_dist", args, retType, true, true, (RpcContext[])null, "dev");
        Map map = res.get(0);
        //System.out.println("Tian: " + map);
        /*CalcOnthologyDistOutput output = getKEUtil().calcWeightedOnthologyDist(
                new CalcOnthologyDistParams().withOnthologySet(termPairs));*/
        CalcWeightedOnthologyDistOutput output = UObject.transformObjectToObject(map, 
                CalcWeightedOnthologyDistOutput.class);
        return output.getOnthologyDistSet();
    }
    
    @Override
    public List<String> getTermRelationTypes() throws Exception {
        return termRelationTypes;
    }
    
    @Override
    public List<TermCategory> getTopTermCategories() throws Exception {
        throw new IllegalStateException("Method is not supported");
    }
    
    @Override
    public List<FeatureOntologyPrediction> listFeatures(
            ListFeaturesParams params) throws Exception {
        String genomeGuid = params.getGenomeGuid() == null ? params.getGenomeRef() : 
            params.getGenomeGuid();
        String kbaseRelAppId = termRelationAppTypes.stream().collect(
                Collectors.toMap(item -> item[0], item -> item[1])).get("kbase");
        List<FeatureOntologyPrediction> ret = new ArrayList<>();
        List<WSFeatureTermPair> pairs = getRE().getWSFeatureTermPairs(
                new GetWSFeatureTermPairsParams().withWsGenomeGuid(genomeGuid)
                .withTargetKeappGuid(kbaseRelAppId));
        Map<String, List<String>> termPairs = new HashMap<>();
        for (WSFeatureTermPair pair : pairs) {
            FeatureOntologyPrediction fop = new FeatureOntologyPrediction()
                    .withFeatureGuid(pair.getFeatureGuid()).withFeatureName(pair.getFeatureName())
                    .withReferenceTermGuid(pair.getRefTermGuid())
                    .withReferenceTermName(pair.getRefTermName())
                    .withKbaseTermGuid(pair.getTargetTermGuid())
                    .withKbaseTermName(pair.getTargetTermName())
                    .withDistance(Double.POSITIVE_INFINITY);
            ret.add(fop);
            if (pair.getRefTermGuid() != null && pair.getTargetTermGuid() != null) {
                String termPairKey = pair.getRefTermGuid() + "_" + pair.getTargetTermGuid();
                termPairs.put(termPairKey, Arrays.asList(pair.getRefTermGuid(), 
                        pair.getTargetTermGuid()));
            }
        }
        if (termPairs.size() > 0) {
            Map<String, Double> termPairToDistance = calcWeightedOnthologyDist(termPairs);
            for (FeatureOntologyPrediction fop : ret) {
                if (fop.getReferenceTermGuid() != null && fop.getKbaseTermGuid() != null) {
                    String termPairKey = fop.getReferenceTermGuid() + "_" + fop.getKbaseTermGuid();
                    Double distance = termPairToDistance.get(termPairKey);
                    if (distance != null) {
                        fop.setDistance((double)distance);
                    }
                }
            }
        }
        Collections.sort(ret, new Comparator<FeatureOntologyPrediction>() {
            @Override
            public int compare(FeatureOntologyPrediction o1,
                    FeatureOntologyPrediction o2) {
                return o2.getDistance().compareTo(o1.getDistance());
            }
        });
        return ret;
    }
}
