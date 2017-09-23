package geneontologydecorator;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonServerMethod;
import us.kbase.common.service.JsonServerServlet;
import us.kbase.common.service.JsonServerSyslog;
import us.kbase.common.service.RpcContext;

//BEGIN_HEADER
import java.net.URL;
//END_HEADER

/**
 * <p>Original spec-file module name: GeneOntologyDecorator</p>
 * <pre>
 * A KBase module: GeneOntologyDecorator
 * </pre>
 */
public class GeneOntologyDecoratorServer extends JsonServerServlet {
    private static final long serialVersionUID = 1L;
    private static final String version = "0.0.1";
    private static final String gitUrl = "https://github.com/psnovichkov/GeneOntologyDecorator.git";
    private static final String gitCommitHash = "054b74f6b648317bd4c7421e8436475538775cf6";

    //BEGIN_CLASS_HEADER
    IGeneOntologyDecoratorImpl impl = null;  //new FakeGeneOntologyDecoratorServer();
    //END_CLASS_HEADER

    public GeneOntologyDecoratorServer() throws Exception {
        super("GeneOntologyDecorator");
        //BEGIN_CONSTRUCTOR
        URL srvWizUrl = new URL(config.get("srv-wiz-url"));
        String keAdminTokenString = config.get("ke-admin-token");
        if (keAdminTokenString == null) {
            throw new IllegalStateException("ke-admin-token is not defined in configuration");
        }
        AuthToken keAdminToken = new AuthToken(keAdminTokenString, "<unknown>");
        impl = new REGeneOntoloryDecoratorImpl(srvWizUrl, keAdminToken);
        //END_CONSTRUCTOR
    }

    /**
     * <p>Original spec-file function name: getTermRelationTypes</p>
     * <pre>
     * </pre>
     * @return   instance of list of original type "term_relation_type"
     */
    @JsonServerMethod(rpc = "GeneOntologyDecorator.getTermRelationTypes", async=true)
    public List<String> getTermRelationTypes(RpcContext jsonRpcContext) throws Exception {
        List<String> returnVal = null;
        //BEGIN getTermRelationTypes
        returnVal = impl.getTermRelationTypes();
        //END getTermRelationTypes
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: getTopTermCategories</p>
     * <pre>
     * </pre>
     * @return   instance of list of type {@link geneontologydecorator.TermCategory TermCategory}
     */
    @JsonServerMethod(rpc = "GeneOntologyDecorator.getTopTermCategories", async=true)
    public List<TermCategory> getTopTermCategories(RpcContext jsonRpcContext) throws Exception {
        List<TermCategory> returnVal = null;
        //BEGIN getTopTermCategories
        returnVal = impl.getTopTermCategories();        
        //END getTopTermCategories
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: getTermRelations</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link geneontologydecorator.GetTermRelationsParams GetTermRelationsParams}
     * @return   instance of mapping from original type "term_relation_type" to type {@link geneontologydecorator.TermProfile TermProfile}
     */
    @JsonServerMethod(rpc = "GeneOntologyDecorator.getTermRelations", async=true)
    public Map<String,TermProfile> getTermRelations(GetTermRelationsParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        Map<String,TermProfile> returnVal = null;
        //BEGIN getTermRelations
        returnVal = impl.getTermRelations(params);
        //END getTermRelations
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: listFeatures</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link geneontologydecorator.ListFeaturesParams ListFeaturesParams}
     * @return   instance of list of type {@link geneontologydecorator.FeatureOntologyPrediction FeatureOntologyPrediction}
     */
    @JsonServerMethod(rpc = "GeneOntologyDecorator.listFeatures", async=true)
    public List<FeatureOntologyPrediction> listFeatures(ListFeaturesParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        List<FeatureOntologyPrediction> returnVal = null;
        //BEGIN listFeatures
        returnVal = impl.listFeatures(params);
        //END listFeatures
        return returnVal;
    }
    @JsonServerMethod(rpc = "GeneOntologyDecorator.status")
    public Map<String, Object> status() {
        Map<String, Object> returnVal = null;
        //BEGIN_STATUS
        returnVal = new LinkedHashMap<String, Object>();
        returnVal.put("state", "OK");
        returnVal.put("message", "");
        returnVal.put("version", version);
        returnVal.put("git_url", gitUrl);
        returnVal.put("git_commit_hash", gitCommitHash);
        //END_STATUS
        return returnVal;
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            new GeneOntologyDecoratorServer().startupServer(Integer.parseInt(args[0]));
        } else if (args.length == 3) {
            JsonServerSyslog.setStaticUseSyslog(false);
            JsonServerSyslog.setStaticMlogFile(args[1] + ".log");
            new GeneOntologyDecoratorServer().processRpcCall(new File(args[0]), new File(args[1]), args[2]);
        } else {
            System.out.println("Usage: <program> <server_port>");
            System.out.println("   or: <program> <context_json_file> <output_json_file> <token>");
            return;
        }
    }
}
