package kbkeutil;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JobState;
import us.kbase.common.service.JsonClientCaller;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.UnauthorizedException;

/**
 * <p>Original spec-file module name: kb_ke_util</p>
 * <pre>
 * A KBase module: kb_ke_util
 * </pre>
 */
public class KbKeUtilClient {
    private JsonClientCaller caller;
    private long asyncJobCheckTimeMs = 100;
    private int asyncJobCheckTimeScalePercent = 150;
    private long asyncJobCheckMaxTimeMs = 300000;  // 5 minutes
    private String serviceVersion = "release";


    /** Constructs a client with a custom URL and no user credentials.
     * @param url the URL of the service.
     */
    public KbKeUtilClient(URL url) {
        caller = new JsonClientCaller(url);
    }
    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param token the user's authorization token.
     * @throws UnauthorizedException if the token is not valid.
     * @throws IOException if an IOException occurs when checking the token's
     * validity.
     */
    public KbKeUtilClient(URL url, AuthToken token) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, token);
    }

    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param user the user name.
     * @param password the password for the user name.
     * @throws UnauthorizedException if the credentials are not valid.
     * @throws IOException if an IOException occurs when checking the user's
     * credentials.
     */
    public KbKeUtilClient(URL url, String user, String password) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, user, password);
    }

    /** Constructs a client with a custom URL
     * and a custom authorization service URL.
     * @param url the URL of the service.
     * @param user the user name.
     * @param password the password for the user name.
     * @param auth the URL of the authorization server.
     * @throws UnauthorizedException if the credentials are not valid.
     * @throws IOException if an IOException occurs when checking the user's
     * credentials.
     */
    public KbKeUtilClient(URL url, String user, String password, URL auth) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, user, password, auth);
    }

    /** Get the token this client uses to communicate with the server.
     * @return the authorization token.
     */
    public AuthToken getToken() {
        return caller.getToken();
    }

    /** Get the URL of the service with which this client communicates.
     * @return the service URL.
     */
    public URL getURL() {
        return caller.getURL();
    }

    /** Set the timeout between establishing a connection to a server and
     * receiving a response. A value of zero or null implies no timeout.
     * @param milliseconds the milliseconds to wait before timing out when
     * attempting to read from a server.
     */
    public void setConnectionReadTimeOut(Integer milliseconds) {
        this.caller.setConnectionReadTimeOut(milliseconds);
    }

    /** Check if this client allows insecure http (vs https) connections.
     * @return true if insecure connections are allowed.
     */
    public boolean isInsecureHttpConnectionAllowed() {
        return caller.isInsecureHttpConnectionAllowed();
    }

    /** Deprecated. Use isInsecureHttpConnectionAllowed().
     * @deprecated
     */
    public boolean isAuthAllowedForHttp() {
        return caller.isAuthAllowedForHttp();
    }

    /** Set whether insecure http (vs https) connections should be allowed by
     * this client.
     * @param allowed true to allow insecure connections. Default false
     */
    public void setIsInsecureHttpConnectionAllowed(boolean allowed) {
        caller.setInsecureHttpConnectionAllowed(allowed);
    }

    /** Deprecated. Use setIsInsecureHttpConnectionAllowed().
     * @deprecated
     */
    public void setAuthAllowedForHttp(boolean isAuthAllowedForHttp) {
        caller.setAuthAllowedForHttp(isAuthAllowedForHttp);
    }

    /** Set whether all SSL certificates, including self-signed certificates,
     * should be trusted.
     * @param trustAll true to trust all certificates. Default false.
     */
    public void setAllSSLCertificatesTrusted(final boolean trustAll) {
        caller.setAllSSLCertificatesTrusted(trustAll);
    }
    
    /** Check if this client trusts all SSL certificates, including
     * self-signed certificates.
     * @return true if all certificates are trusted.
     */
    public boolean isAllSSLCertificatesTrusted() {
        return caller.isAllSSLCertificatesTrusted();
    }
    /** Sets streaming mode on. In this case, the data will be streamed to
     * the server in chunks as it is read from disk rather than buffered in
     * memory. Many servers are not compatible with this feature.
     * @param streamRequest true to set streaming mode on, false otherwise.
     */
    public void setStreamingModeOn(boolean streamRequest) {
        caller.setStreamingModeOn(streamRequest);
    }

    /** Returns true if streaming mode is on.
     * @return true if streaming mode is on.
     */
    public boolean isStreamingModeOn() {
        return caller.isStreamingModeOn();
    }

    public void _setFileForNextRpcResponse(File f) {
        caller.setFileForNextRpcResponse(f);
    }

    public long getAsyncJobCheckTimeMs() {
        return this.asyncJobCheckTimeMs;
    }

    public void setAsyncJobCheckTimeMs(long newValue) {
        this.asyncJobCheckTimeMs = newValue;
    }

    public int getAsyncJobCheckTimeScalePercent() {
        return this.asyncJobCheckTimeScalePercent;
    }

    public void setAsyncJobCheckTimeScalePercent(int newValue) {
        this.asyncJobCheckTimeScalePercent = newValue;
    }

    public long getAsyncJobCheckMaxTimeMs() {
        return this.asyncJobCheckMaxTimeMs;
    }

    public void setAsyncJobCheckMaxTimeMs(long newValue) {
        this.asyncJobCheckMaxTimeMs = newValue;
    }

    public String getServiceVersion() {
        return this.serviceVersion;
    }

    public void setServiceVersion(String newValue) {
        this.serviceVersion = newValue;
    }

    protected <T> JobState<T> _checkJob(String jobId, TypeReference<List<JobState<T>>> retType) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(jobId);
        List<JobState<T>> res = caller.jsonrpcCall("kb_ke_util._check_job", args, retType, true, true);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: run_pdist</p>
     * <pre>
     * run_pdist: a wrapper method for scipy.spatial.distance.pdist
     * reference: 
     * https://docs.scipy.org/doc/scipy/reference/generated/scipy.spatial.distance.pdist.html
     * </pre>
     * @param   params   instance of type {@link kbkeutil.PdistParams PdistParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.PdistOutput PdistOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _runPdistSubmit(PdistParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("kb_ke_util._run_pdist_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: run_pdist</p>
     * <pre>
     * run_pdist: a wrapper method for scipy.spatial.distance.pdist
     * reference: 
     * https://docs.scipy.org/doc/scipy/reference/generated/scipy.spatial.distance.pdist.html
     * </pre>
     * @param   params   instance of type {@link kbkeutil.PdistParams PdistParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.PdistOutput PdistOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public PdistOutput runPdist(PdistParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _runPdistSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<PdistOutput>>>> retType = new TypeReference<List<JobState<List<PdistOutput>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<PdistOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: run_linkage</p>
     * <pre>
     * run_linkage: a wrapper method for scipy.cluster.hierarchy.linkage
     * reference: 
     * https://docs.scipy.org/doc/scipy/reference/generated/scipy.cluster.hierarchy.linkage.html
     * </pre>
     * @param   params   instance of type {@link kbkeutil.LinkageParams LinkageParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.LinkageOutput LinkageOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _runLinkageSubmit(LinkageParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("kb_ke_util._run_linkage_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: run_linkage</p>
     * <pre>
     * run_linkage: a wrapper method for scipy.cluster.hierarchy.linkage
     * reference: 
     * https://docs.scipy.org/doc/scipy/reference/generated/scipy.cluster.hierarchy.linkage.html
     * </pre>
     * @param   params   instance of type {@link kbkeutil.LinkageParams LinkageParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.LinkageOutput LinkageOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public LinkageOutput runLinkage(LinkageParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _runLinkageSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<LinkageOutput>>>> retType = new TypeReference<List<JobState<List<LinkageOutput>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<LinkageOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: run_fcluster</p>
     * <pre>
     * run_fcluster: a wrapper method for scipy.cluster.hierarchy.fcluster
     * reference: 
     * https://docs.scipy.org/doc/scipy/reference/generated/scipy.cluster.hierarchy.fcluster.html
     * </pre>
     * @param   params   instance of type {@link kbkeutil.FclusterParams FclusterParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.FclusterOutput FclusterOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _runFclusterSubmit(FclusterParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("kb_ke_util._run_fcluster_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: run_fcluster</p>
     * <pre>
     * run_fcluster: a wrapper method for scipy.cluster.hierarchy.fcluster
     * reference: 
     * https://docs.scipy.org/doc/scipy/reference/generated/scipy.cluster.hierarchy.fcluster.html
     * </pre>
     * @param   params   instance of type {@link kbkeutil.FclusterParams FclusterParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.FclusterOutput FclusterOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public FclusterOutput runFcluster(FclusterParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _runFclusterSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<FclusterOutput>>>> retType = new TypeReference<List<JobState<List<FclusterOutput>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<FclusterOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: run_dendrogram</p>
     * <pre>
     * run_dendrogram: a wrapper method for scipy.cluster.hierarchy.dendrogram
     * reference: 
     * https://docs.scipy.org/doc/scipy/reference/generated/scipy.cluster.hierarchy.dendrogram.html
     * </pre>
     * @param   params   instance of type {@link kbkeutil.DendrogramParams DendrogramParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.DendrogramOutput DendrogramOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _runDendrogramSubmit(DendrogramParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("kb_ke_util._run_dendrogram_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: run_dendrogram</p>
     * <pre>
     * run_dendrogram: a wrapper method for scipy.cluster.hierarchy.dendrogram
     * reference: 
     * https://docs.scipy.org/doc/scipy/reference/generated/scipy.cluster.hierarchy.dendrogram.html
     * </pre>
     * @param   params   instance of type {@link kbkeutil.DendrogramParams DendrogramParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.DendrogramOutput DendrogramOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public DendrogramOutput runDendrogram(DendrogramParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _runDendrogramSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<DendrogramOutput>>>> retType = new TypeReference<List<JobState<List<DendrogramOutput>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<DendrogramOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: build_biclusters</p>
     * <pre>
     * build_biclusters: build biclusters and store result feature sets as JSON into shock
     * </pre>
     * @param   params   instance of type {@link kbkeutil.BuildBiclustersParams BuildBiclustersParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.BuildBiclustersOutput BuildBiclustersOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _buildBiclustersSubmit(BuildBiclustersParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("kb_ke_util._build_biclusters_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: build_biclusters</p>
     * <pre>
     * build_biclusters: build biclusters and store result feature sets as JSON into shock
     * </pre>
     * @param   params   instance of type {@link kbkeutil.BuildBiclustersParams BuildBiclustersParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.BuildBiclustersOutput BuildBiclustersOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public BuildBiclustersOutput buildBiclusters(BuildBiclustersParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _buildBiclustersSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<BuildBiclustersOutput>>>> retType = new TypeReference<List<JobState<List<BuildBiclustersOutput>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<BuildBiclustersOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: enrich_onthology</p>
     * <pre>
     * enrich_onthology: run GO term enrichment analysis
     * </pre>
     * @param   params   instance of type {@link kbkeutil.EnrichOnthologyParams EnrichOnthologyParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.EnrichOnthologyOutput EnrichOnthologyOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _enrichOnthologySubmit(EnrichOnthologyParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("kb_ke_util._enrich_onthology_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: enrich_onthology</p>
     * <pre>
     * enrich_onthology: run GO term enrichment analysis
     * </pre>
     * @param   params   instance of type {@link kbkeutil.EnrichOnthologyParams EnrichOnthologyParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.EnrichOnthologyOutput EnrichOnthologyOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public EnrichOnthologyOutput enrichOnthology(EnrichOnthologyParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _enrichOnthologySubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<EnrichOnthologyOutput>>>> retType = new TypeReference<List<JobState<List<EnrichOnthologyOutput>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<EnrichOnthologyOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: calc_onthology_dist</p>
     * <pre>
     * calc_onthology_dist: calculate onthology distance
     *                      (sum of steps for each node in onthology_pair travels to 
     *                       the nearest common ancestor node)
     *                      NOTE: return inf if no common ancestor node found
     * </pre>
     * @param   params   instance of type {@link kbkeutil.CalcOnthologyDistParams CalcOnthologyDistParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.CalcOnthologyDistOutput CalcOnthologyDistOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _calcOnthologyDistSubmit(CalcOnthologyDistParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("kb_ke_util._calc_onthology_dist_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: calc_onthology_dist</p>
     * <pre>
     * calc_onthology_dist: calculate onthology distance
     *                      (sum of steps for each node in onthology_pair travels to 
     *                       the nearest common ancestor node)
     *                      NOTE: return inf if no common ancestor node found
     * </pre>
     * @param   params   instance of type {@link kbkeutil.CalcOnthologyDistParams CalcOnthologyDistParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.CalcOnthologyDistOutput CalcOnthologyDistOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public CalcOnthologyDistOutput calcOnthologyDist(CalcOnthologyDistParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _calcOnthologyDistSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<CalcOnthologyDistOutput>>>> retType = new TypeReference<List<JobState<List<CalcOnthologyDistOutput>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<CalcOnthologyDistOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: calc_weighted_onthology_dist</p>
     * <pre>
     * calc_weighted_onthology_dist: calculate weighted onthology distance
     *                               (edges are weighted from root to leaves
     *                                root edges are weighted 1/2
     *                                each child's edge weights half of its parent's edge)
     *                               NOTE: return inf if no common ancestor node found
     * </pre>
     * @param   params   instance of type {@link kbkeutil.CalcOnthologyDistParams CalcOnthologyDistParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.CalcOnthologyDistOutput CalcOnthologyDistOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _calcWeightedOnthologyDistSubmit(CalcOnthologyDistParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("kb_ke_util._calc_weighted_onthology_dist_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: calc_weighted_onthology_dist</p>
     * <pre>
     * calc_weighted_onthology_dist: calculate weighted onthology distance
     *                               (edges are weighted from root to leaves
     *                                root edges are weighted 1/2
     *                                each child's edge weights half of its parent's edge)
     *                               NOTE: return inf if no common ancestor node found
     * </pre>
     * @param   params   instance of type {@link kbkeutil.CalcOnthologyDistParams CalcOnthologyDistParams}
     * @return   parameter "returnVal" of type {@link kbkeutil.CalcOnthologyDistOutput CalcOnthologyDistOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public CalcOnthologyDistOutput calcWeightedOnthologyDist(CalcOnthologyDistParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _calcWeightedOnthologyDistSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<CalcOnthologyDistOutput>>>> retType = new TypeReference<List<JobState<List<CalcOnthologyDistOutput>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<CalcOnthologyDistOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    public Map<String, Object> status(RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<String>> retType1 = new TypeReference<List<String>>() {};
        List<String> res1 = caller.jsonrpcCall("kb_ke_util._status_submit", args, retType1, true, true, jsonRpcContext);
        String jobId = res1.get(0);
        TypeReference<List<JobState<List<Map<String, Object>>>>> retType2 = new TypeReference<List<JobState<List<Map<String, Object>>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<Map<String, Object>>> res2 = _checkJob(jobId, retType2);
            if (res2.getFinished() != 0L)
                return res2.getResult().get(0);
        }
    }
}
