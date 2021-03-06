
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
 * <p>Original spec-file type: ListFeaturesParams</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "genome_ref",
    "genome_guid"
})
public class ListFeaturesParams {

    @JsonProperty("genome_ref")
    private String genomeRef;
    @JsonProperty("genome_guid")
    private String genomeGuid;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("genome_ref")
    public String getGenomeRef() {
        return genomeRef;
    }

    @JsonProperty("genome_ref")
    public void setGenomeRef(String genomeRef) {
        this.genomeRef = genomeRef;
    }

    public ListFeaturesParams withGenomeRef(String genomeRef) {
        this.genomeRef = genomeRef;
        return this;
    }

    @JsonProperty("genome_guid")
    public String getGenomeGuid() {
        return genomeGuid;
    }

    @JsonProperty("genome_guid")
    public void setGenomeGuid(String genomeGuid) {
        this.genomeGuid = genomeGuid;
    }

    public ListFeaturesParams withGenomeGuid(String genomeGuid) {
        this.genomeGuid = genomeGuid;
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
        return ((((((("ListFeaturesParams"+" [genomeRef=")+ genomeRef)+", genomeGuid=")+ genomeGuid)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
