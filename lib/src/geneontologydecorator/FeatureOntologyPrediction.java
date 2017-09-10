
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
    "feature_guid",
    "feature_name",
    "distance",
    "reference_term_name",
    "reference_term_guid",
    "kbase_term_name",
    "kbase_term_guid"
})
public class FeatureOntologyPrediction {

    @JsonProperty("feature_guid")
    private String featureGuid;
    @JsonProperty("feature_name")
    private String featureName;
    @JsonProperty("distance")
    private Double distance;
    @JsonProperty("reference_term_name")
    private String referenceTermName;
    @JsonProperty("reference_term_guid")
    private String referenceTermGuid;
    @JsonProperty("kbase_term_name")
    private String kbaseTermName;
    @JsonProperty("kbase_term_guid")
    private String kbaseTermGuid;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("feature_guid")
    public String getFeatureGuid() {
        return featureGuid;
    }

    @JsonProperty("feature_guid")
    public void setFeatureGuid(String featureGuid) {
        this.featureGuid = featureGuid;
    }

    public FeatureOntologyPrediction withFeatureGuid(String featureGuid) {
        this.featureGuid = featureGuid;
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

    @JsonProperty("reference_term_name")
    public String getReferenceTermName() {
        return referenceTermName;
    }

    @JsonProperty("reference_term_name")
    public void setReferenceTermName(String referenceTermName) {
        this.referenceTermName = referenceTermName;
    }

    public FeatureOntologyPrediction withReferenceTermName(String referenceTermName) {
        this.referenceTermName = referenceTermName;
        return this;
    }

    @JsonProperty("reference_term_guid")
    public String getReferenceTermGuid() {
        return referenceTermGuid;
    }

    @JsonProperty("reference_term_guid")
    public void setReferenceTermGuid(String referenceTermGuid) {
        this.referenceTermGuid = referenceTermGuid;
    }

    public FeatureOntologyPrediction withReferenceTermGuid(String referenceTermGuid) {
        this.referenceTermGuid = referenceTermGuid;
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

    @JsonProperty("kbase_term_guid")
    public String getKbaseTermGuid() {
        return kbaseTermGuid;
    }

    @JsonProperty("kbase_term_guid")
    public void setKbaseTermGuid(String kbaseTermGuid) {
        this.kbaseTermGuid = kbaseTermGuid;
    }

    public FeatureOntologyPrediction withKbaseTermGuid(String kbaseTermGuid) {
        this.kbaseTermGuid = kbaseTermGuid;
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
        return ((((((((((((((((("FeatureOntologyPrediction"+" [featureGuid=")+ featureGuid)+", featureName=")+ featureName)+", distance=")+ distance)+", referenceTermName=")+ referenceTermName)+", referenceTermGuid=")+ referenceTermGuid)+", kbaseTermName=")+ kbaseTermName)+", kbaseTermGuid=")+ kbaseTermGuid)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
