
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
 * <p>Original spec-file type: Term</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "term_guid",
    "term_name",
    "term_position",
    "pvalue"
})
public class Term {

    @JsonProperty("term_guid")
    private String termGuid;
    @JsonProperty("term_name")
    private String termName;
    @JsonProperty("term_position")
    private Double termPosition;
    @JsonProperty("pvalue")
    private Double pvalue;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("term_guid")
    public String getTermGuid() {
        return termGuid;
    }

    @JsonProperty("term_guid")
    public void setTermGuid(String termGuid) {
        this.termGuid = termGuid;
    }

    public Term withTermGuid(String termGuid) {
        this.termGuid = termGuid;
        return this;
    }

    @JsonProperty("term_name")
    public String getTermName() {
        return termName;
    }

    @JsonProperty("term_name")
    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Term withTermName(String termName) {
        this.termName = termName;
        return this;
    }

    @JsonProperty("term_position")
    public Double getTermPosition() {
        return termPosition;
    }

    @JsonProperty("term_position")
    public void setTermPosition(Double termPosition) {
        this.termPosition = termPosition;
    }

    public Term withTermPosition(Double termPosition) {
        this.termPosition = termPosition;
        return this;
    }

    @JsonProperty("pvalue")
    public Double getPvalue() {
        return pvalue;
    }

    @JsonProperty("pvalue")
    public void setPvalue(Double pvalue) {
        this.pvalue = pvalue;
    }

    public Term withPvalue(Double pvalue) {
        this.pvalue = pvalue;
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
        return ((((((((((("Term"+" [termGuid=")+ termGuid)+", termName=")+ termName)+", termPosition=")+ termPosition)+", pvalue=")+ pvalue)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
