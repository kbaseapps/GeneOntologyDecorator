
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
 * <p>Original spec-file type: GetTermRelationsParams</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "feature_id"
})
public class GetTermRelationsParams {

    @JsonProperty("feature_id")
    private String featureId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("feature_id")
    public String getFeatureId() {
        return featureId;
    }

    @JsonProperty("feature_id")
    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public GetTermRelationsParams withFeatureId(String featureId) {
        this.featureId = featureId;
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
        return ((((("GetTermRelationsParams"+" [featureId=")+ featureId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
