
package geneontologydecorator;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: FeatureOntologyPrediction</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "feature_id",
    "feature_name",
    "distance",
    "community_term_name",
    "community_term_id",
    "kbase_term_name",
    "kbase_term_id"
})
public class FeatureOntologyPrediction {

    @JsonProperty("feature_id")
    private String featureId;
    @JsonProperty("feature_name")
    private String featureName;
    @JsonProperty("distance")
    private Double distance;
    @JsonProperty("community_term_name")
    private String communityTermName;
    @JsonProperty("community_term_id")
    private String communityTermId;
    @JsonProperty("kbase_term_name")
    private String kbaseTermName;
    @JsonProperty("kbase_term_id")
    private String kbaseTermId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("feature_id")
    public String getFeatureId() {
        return featureId;
    }

    @JsonProperty("feature_id")
    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public FeatureOntologyPrediction withFeatureId(String featureId) {
        this.featureId = featureId;
        return this;
    }

    @JsonProperty("feature_name")
    public String getFeatureName() {
        return featureName;
    }

    @JsonProperty("feature_name")
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public FeatureOntologyPrediction withFeatureName(String featureName) {
        this.featureName = featureName;
        return this;
    }

    @JsonProperty("distance")
    public Double getDistance() {
        return distance;
    }

    @JsonProperty("distance")
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public FeatureOntologyPrediction withDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    @JsonProperty("community_term_name")
    public String getCommunityTermName() {
        return communityTermName;
    }

    @JsonProperty("community_term_name")
    public void setCommunityTermName(String communityTermName) {
        this.communityTermName = communityTermName;
    }

    public FeatureOntologyPrediction withCommunityTermName(String communityTermName) {
        this.communityTermName = communityTermName;
        return this;
    }

    @JsonProperty("community_term_id")
    public String getCommunityTermId() {
        return communityTermId;
    }

    @JsonProperty("community_term_id")
    public void setCommunityTermId(String communityTermId) {
        this.communityTermId = communityTermId;
    }

    public FeatureOntologyPrediction withCommunityTermId(String communityTermId) {
        this.communityTermId = communityTermId;
        return this;
    }

    @JsonProperty("kbase_term_name")
    public String getKbaseTermName() {
        return kbaseTermName;
    }

    @JsonProperty("kbase_term_name")
    public void setKbaseTermName(String kbaseTermName) {
        this.kbaseTermName = kbaseTermName;
    }

    public FeatureOntologyPrediction withKbaseTermName(String kbaseTermName) {
        this.kbaseTermName = kbaseTermName;
        return this;
    }

    @JsonProperty("kbase_term_id")
    public String getKbaseTermId() {
        return kbaseTermId;
    }

    @JsonProperty("kbase_term_id")
    public void setKbaseTermId(String kbaseTermId) {
        this.kbaseTermId = kbaseTermId;
    }

    public FeatureOntologyPrediction withKbaseTermId(String kbaseTermId) {
        this.kbaseTermId = kbaseTermId;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return ((((((((((((((((("FeatureOntologyPrediction"+" [featureId=")+ featureId)+", featureName=")+ featureName)+", distance=")+ distance)+", communityTermName=")+ communityTermName)+", communityTermId=")+ communityTermId)+", kbaseTermName=")+ kbaseTermName)+", kbaseTermId=")+ kbaseTermId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
