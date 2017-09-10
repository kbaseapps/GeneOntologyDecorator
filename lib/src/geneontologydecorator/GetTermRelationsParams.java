
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
    "feature_guid"
})
public class GetTermRelationsParams {

    @JsonProperty("feature_guid")
    private String featureGuid;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("feature_guid")
    public String getFeatureGuid() {
        return featureGuid;
    }

    @JsonProperty("feature_guid")
    public void setFeatureGuid(String featureGuid) {
        this.featureGuid = featureGuid;
    }

    public GetTermRelationsParams withFeatureGuid(String featureGuid) {
        this.featureGuid = featureGuid;
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
        return ((((("GetTermRelationsParams"+" [featureGuid=")+ featureGuid)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
