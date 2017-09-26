package geneontologydecorator.test;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import junit.framework.Assert;

import org.ini4j.Ini;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import geneontologydecorator.FeatureOntologyPrediction;
import geneontologydecorator.GeneOntologyDecoratorServer;
import geneontologydecorator.GetTermRelationsParams;
import geneontologydecorator.ListFeaturesParams;
import geneontologydecorator.Term;
import geneontologydecorator.TermProfile;
import us.kbase.auth.AuthConfig;
import us.kbase.auth.AuthToken;
import us.kbase.auth.ConfigurableAuthService;
import us.kbase.common.service.JsonServerSyslog;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.UObject;
import us.kbase.workspace.CreateWorkspaceParams;
import us.kbase.workspace.ProvenanceAction;
import us.kbase.workspace.WorkspaceClient;
import us.kbase.workspace.WorkspaceIdentity;

public class GeneOntologyDecoratorServerTest {
    private static AuthToken token = null;
    private static Map<String, String> config = null;
    private static WorkspaceClient wsClient = null;
    private static String wsName = null;
    private static GeneOntologyDecoratorServer impl = null;
    private static Path scratch;
    private static URL callbackURL;
    
    @BeforeClass
    public static void init() throws Exception {
        // Config loading
        String configFilePath = System.getenv("KB_DEPLOYMENT_CONFIG");
        File deploy = new File(configFilePath);
        Ini ini = new Ini(deploy);
        config = ini.get("GeneOntologyDecorator");
        // Token validation
        String authUrl = config.get("auth-service-url");
        String authUrlInsecure = config.get("auth-service-url-allow-insecure");
        ConfigurableAuthService authService = new ConfigurableAuthService(
                new AuthConfig().withKBaseAuthServerURL(new URL(authUrl))
                .withAllowInsecureURLs("true".equals(authUrlInsecure)));
        token = authService.validateToken(System.getenv("KB_AUTH_TOKEN"));
        // Reading URLs from config
        wsClient = new WorkspaceClient(new URL(config.get("workspace-url")), token);
        wsClient.setIsInsecureHttpConnectionAllowed(true); // do we need this?
        callbackURL = new URL(System.getenv("SDK_CALLBACK_URL"));
        scratch = Paths.get(config.get("scratch"));
        // These lines are necessary because we don't want to start linux syslog bridge service
        JsonServerSyslog.setStaticUseSyslog(false);
        JsonServerSyslog.setStaticMlogFile(new File(config.get("scratch"), "test.log")
            .getAbsolutePath());
        impl = new GeneOntologyDecoratorServer();
    }
    
    private static String getWsName() throws Exception {
        if (wsName == null) {
            long suffix = System.currentTimeMillis();
            wsName = "test_GeneOntologyDecorator_" + suffix;
            wsClient.createWorkspace(new CreateWorkspaceParams().withWorkspace(wsName));
        }
        return wsName;
    }
    
    private static RpcContext getContext() {
        return new RpcContext().withProvenance(Arrays.asList(new ProvenanceAction()
            .withService("GeneOntologyDecorator").withMethod("please_never_use_it_in_production")
            .withMethodParams(new ArrayList<UObject>())));
    }
    
    @AfterClass
    public static void cleanup() {
        if (wsName != null) {
            try {
                wsClient.deleteWorkspace(new WorkspaceIdentity().withWorkspace(wsName));
                System.out.println("Test workspace was deleted");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void testFeatureListAndBadges() throws Exception {
        String inputGenomeRef = "25582/31/1";
        // Feature list
        List<FeatureOntologyPrediction> ret = impl.listFeatures(
                new ListFeaturesParams().withGenomeRef(inputGenomeRef), token, null);
        System.out.println("Feature list size: " + ret.size());
        System.out.println("First item: " + ret.get(0));
        String featureGuid = ret.get(0).getFeatureGuid();
        // Badge
        Map<String, TermProfile> profiles = impl.getTermRelations(new GetTermRelationsParams()
                .withFeatureGuid(featureGuid), token, null);
        System.out.println("Badge: " + profiles);
    }

    @Test
    public void testErrorBadge() throws Exception {
        String inputGenomeRef = "26100/13/1";
        // Feature list
        List<FeatureOntologyPrediction> ret = impl.listFeatures(
                new ListFeaturesParams().withGenomeRef(inputGenomeRef), token, null);
        String featureGuid = null;
        for (FeatureOntologyPrediction fop : ret) {
            if (fop.getFeatureName().equals("GeneID:4476881")) {
                featureGuid = fop.getFeatureGuid();
                break;
            }
        }
        // Badge
        Map<String, TermProfile> profiles = impl.getTermRelations(new GetTermRelationsParams()
                .withFeatureGuid(featureGuid), token, null);
        for (String key : profiles.keySet()) {
            if (key.equals("reference")) {
                continue;
            }
            TermProfile tp = profiles.get(key);
            for (Term t : tp.getTerms()) {
                Assert.assertTrue(t.getTermPosition() < 100);
            }
        }
    }
}
