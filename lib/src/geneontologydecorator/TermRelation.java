
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
 * <p>Original spec-file type: TermRelation</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "relation_type",
    "term_position",
    "term_id",
    "term_name"
})
public class TermRelation {

    @JsonProperty("relation_type")
    private String relationType;
    @JsonProperty("term_position")
    private Double termPosition;
    @JsonProperty("term_id")
    private String termId;
    @JsonProperty("term_name")
    private String termName;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("relation_type")
    public String getRelationType() {
        return relationType;
    }

    @JsonProperty("relation_type")
    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public TermRelation withRelationType(String relationType) {
        this.relationType = relationType;
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

    public TermRelation withTermPosition(Double termPosition) {
        this.termPosition = termPosition;
        return this;
    }

    @JsonProperty("term_id")
    public String getTermId() {
        return termId;
    }

    @JsonProperty("term_id")
    public void setTermId(String termId) {
        this.termId = termId;
    }

    public TermRelation withTermId(String termId) {
        this.termId = termId;
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

    public TermRelation withTermName(String termName) {
        this.termName = termName;
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
        return ((((((((((("TermRelation"+" [relationType=")+ relationType)+", termPosition=")+ termPosition)+", termId=")+ termId)+", termName=")+ termName)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
