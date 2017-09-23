package geneontologydecorator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import kbaserelationengine.GetWSFeatureTermEnrichmentProfilesOutput;
import kbaserelationengine.GetWSFeatureTermEnrichmentProfilesParams;
import kbaserelationengine.GetWSFeatureTermPairsParams;
import kbaserelationengine.KBaseRelationEngineServiceClient;
import kbaserelationengine.TermEnrichment;
import kbaserelationengine.TermEnrichmentProfile;
import kbaserelationengine.WSFeatureTermPair;
import kbkeutil.CalcOnthologyDistOutput;
import kbkeutil.CalcOnthologyDistParams;
import kbkeutil.KbKeUtilServiceClient;
import us.kbase.auth.AuthToken;
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
                .withTermName(output.getRefTermName());
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
                String termPairKey = refTerm.getTermGuid() + "_" + t.getTermGuid();
                termPairs.put(termPairKey, Arrays.asList(refTerm.getTermGuid(), t.getTermGuid()));
            }
            Term bestTerm = terms.stream().min(
                    (t1, t2) -> Double.compare(t1.getPvalue(), t2.getPvalue())).get();
            TermProfile outProfile = new TermProfile();
            outProfile.withBestTerm(bestTerm).withTerms(terms);
            ret.put(termRelType, outProfile);
        }
        Map<String, Long> termPairToDistance = calcWeightedOnthologyDist(termPairs);
        for (TermProfile profile : ret.values()) {
            for (Term t : profile.getTerms()) {
                String termPairKey = refTerm.getTermGuid() + "_" + t.getTermGuid();
                Long distance = termPairToDistance.get(termPairKey);
                if (distance != null) {
                    t.setTermPosition((double)(long)distance);
                }
            }
        }
        return ret;
    }

    private Map<String, Long> calcWeightedOnthologyDist(
            Map<String, List<String>> termPairs) throws Exception {
        CalcOnthologyDistOutput output = getKEUtil().calcWeightedOnthologyDist(
                new CalcOnthologyDistParams().withOnthologySet(termPairs));
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
                    .withKbaseTermName(pair.getTargetTermName());
            ret.add(fop);
            String termPairKey = pair.getRefTermGuid() + "_" + pair.getTargetTermGuid();
            termPairs.put(termPairKey, Arrays.asList(pair.getRefTermGuid(), 
                    pair.getTargetTermGuid()));
        }
        Map<String, Long> termPairToDistance = calcWeightedOnthologyDist(termPairs);
        for (FeatureOntologyPrediction fop : ret) {
            String termPairKey = fop.getReferenceTermGuid() + "_" + fop.getKbaseTermGuid();
            Long distance = termPairToDistance.get(termPairKey);
            if (distance != null) {
                fop.setDistance((double)(long)distance);
            }
        }
        return ret;
    }
}
