
package geneontologydecorator;

import java.util.HashMap;
import java.util.List;
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
    "feature_aliases",
    "feature_function",
    "with_expression",
    "with_fitness",
    "distance",
    "reference_term_name",
    "reference_term_guid",
    "kbase_term_name",
    "kbase_term_guid"
})
public class FeatureOntologyPrediction {

    @JsonProperty("feature_guid")
    private java.lang.String featureGuid;
    @JsonProperty("feature_name")
    private java.lang.String featureName;
    @JsonProperty("feature_aliases")
    private List<String> featureAliases;
    @JsonProperty("feature_function")
    private java.lang.String featureFunction;
    @JsonProperty("with_expression")
    private Long withExpression;
    @JsonProperty("with_fitness")
    private Long withFitness;
    @JsonProperty("distance")
    private Double distance;
    @JsonProperty("reference_term_name")
    private java.lang.String referenceTermName;
    @JsonProperty("reference_term_guid")
    private java.lang.String referenceTermGuid;
    @JsonProperty("kbase_term_name")
    private java.lang.String kbaseTermName;
    @JsonProperty("kbase_term_guid")
    private java.lang.String kbaseTermGuid;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("feature_guid")
    public java.lang.String getFeatureGuid() {
        return featureGuid;
    }

    @JsonProperty("feature_guid")
    public void setFeatureGuid(java.lang.String featureGuid) {
        this.featureGuid = featureGuid;
    }

    public FeatureOntologyPrediction withFeatureGuid(java.lang.String featureGuid) {
        this.featureGuid = featureGuid;
        return this;
    }

    @JsonProperty("feature_name")
    public java.lang.String getFeatureName() {
        return featureName;
    }

    @JsonProperty("feature_name")
    public void setFeatureName(java.lang.String featureName) {
        this.featureName = featureName;
    }

    public FeatureOntologyPrediction withFeatureName(java.lang.String featureName) {
        this.featureName = featureName;
        return this;
    }

    @JsonProperty("feature_aliases")
    public List<String> getFeatureAliases() {
        return featureAliases;
    }

    @JsonProperty("feature_aliases")
    public void setFeatureAliases(List<String> featureAliases) {
        this.featureAliases = featureAliases;
    }

    public FeatureOntologyPrediction withFeatureAliases(List<String> featureAliases) {
        this.featureAliases = featureAliases;
        return this;
    }

    @JsonProperty("feature_function")
    public java.lang.String getFeatureFunction() {
        return featureFunction;
    }

    @JsonProperty("feature_function")
    public void setFeatureFunction(java.lang.String featureFunction) {
        this.featureFunction = featureFunction;
    }

    public FeatureOntologyPrediction withFeatureFunction(java.lang.String featureFunction) {
        this.featureFunction = featureFunction;
        return this;
    }

    @JsonProperty("with_expression")
    public Long getWithExpression() {
        return withExpression;
    }

    @JsonProperty("with_expression")
    public void setWithExpression(Long withExpression) {
        this.withExpression = withExpression;
    }

    public FeatureOntologyPrediction withWithExpression(Long withExpression) {
        this.withExpression = withExpression;
        return this;
    }

    @JsonProperty("with_fitness")
    public Long getWithFitness() {
        return withFitness;
    }

    @JsonProperty("with_fitness")
    public void setWithFitness(Long withFitness) {
        this.withFitness = withFitness;
    }

    public FeatureOntologyPrediction withWithFitness(Long withFitness) {
        this.withFitness = withFitness;
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
    public java.lang.String getReferenceTermName() {
        return referenceTermName;
    }

    @JsonProperty("reference_term_name")
    public void setReferenceTermName(java.lang.String referenceTermName) {
        this.referenceTermName = referenceTermName;
    }

    public FeatureOntologyPrediction withReferenceTermName(java.lang.String referenceTermName) {
        this.referenceTermName = referenceTermName;
        return this;
    }

    @JsonProperty("reference_term_guid")
    public java.lang.String getReferenceTermGuid() {
        return referenceTermGuid;
    }

    @JsonProperty("reference_term_guid")
    public void setReferenceTermGuid(java.lang.String referenceTermGuid) {
        this.referenceTermGuid = referenceTermGuid;
    }

    public FeatureOntologyPrediction withReferenceTermGuid(java.lang.String referenceTermGuid) {
        this.referenceTermGuid = referenceTermGuid;
        return this;
    }

    @JsonProperty("kbase_term_name")
    public java.lang.String getKbaseTermName() {
        return kbaseTermName;
    }

    @JsonProperty("kbase_term_name")
    public void setKbaseTermName(java.lang.String kbaseTermName) {
        this.kbaseTermName = kbaseTermName;
    }

    public FeatureOntologyPrediction withKbaseTermName(java.lang.String kbaseTermName) {
        this.kbaseTermName = kbaseTermName;
        return this;
    }

    @JsonProperty("kbase_term_guid")
    public java.lang.String getKbaseTermGuid() {
        return kbaseTermGuid;
    }

    @JsonProperty("kbase_term_guid")
    public void setKbaseTermGuid(java.lang.String kbaseTermGuid) {
        this.kbaseTermGuid = kbaseTermGuid;
    }

    public FeatureOntologyPrediction withKbaseTermGuid(java.lang.String kbaseTermGuid) {
        this.kbaseTermGuid = kbaseTermGuid;
        return this;
    }

    @JsonAnyGetter
    public Map<java.lang.String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(java.lang.String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public java.lang.String toString() {
        return ((((((((((((((((((((((((("FeatureOntologyPrediction"+" [featureGuid=")+ featureGuid)+", featureName=")+ featureName)+", featureAliases=")+ featureAliases)+", featureFunction=")+ featureFunction)+", withExpression=")+ withExpression)+", withFitness=")+ withFitness)+", distance=")+ distance)+", referenceTermName=")+ referenceTermName)+", referenceTermGuid=")+ referenceTermGuid)+", kbaseTermName=")+ kbaseTermName)+", kbaseTermGuid=")+ kbaseTermGuid)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
