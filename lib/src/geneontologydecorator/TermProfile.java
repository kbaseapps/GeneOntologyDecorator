
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
 * <p>Original spec-file type: TermProfile</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "best_term",
    "terms"
})
public class TermProfile {

    /**
     * <p>Original spec-file type: Term</p>
     * 
     * 
     */
    @JsonProperty("best_term")
    private geneontologydecorator.Term bestTerm;
    @JsonProperty("terms")
    private List<geneontologydecorator.Term> terms;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * <p>Original spec-file type: Term</p>
     * 
     * 
     */
    @JsonProperty("best_term")
    public geneontologydecorator.Term getBestTerm() {
        return bestTerm;
    }

    /**
     * <p>Original spec-file type: Term</p>
     * 
     * 
     */
    @JsonProperty("best_term")
    public void setBestTerm(geneontologydecorator.Term bestTerm) {
        this.bestTerm = bestTerm;
    }

    public TermProfile withBestTerm(geneontologydecorator.Term bestTerm) {
        this.bestTerm = bestTerm;
        return this;
    }

    @JsonProperty("terms")
    public List<geneontologydecorator.Term> getTerms() {
        return terms;
    }

    @JsonProperty("terms")
    public void setTerms(List<geneontologydecorator.Term> terms) {
        this.terms = terms;
    }

    public TermProfile withTerms(List<geneontologydecorator.Term> terms) {
        this.terms = terms;
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
        return ((((((("TermProfile"+" [bestTerm=")+ bestTerm)+", terms=")+ terms)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
