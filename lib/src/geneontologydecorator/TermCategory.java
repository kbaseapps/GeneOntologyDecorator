
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
 * <p>Original spec-file type: TermCategory</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "category_name",
    "position_from",
    "position_to"
})
public class TermCategory {

    @JsonProperty("category_name")
    private String categoryName;
    @JsonProperty("position_from")
    private Double positionFrom;
    @JsonProperty("position_to")
    private Double positionTo;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("category_name")
    public String getCategoryName() {
        return categoryName;
    }

    @JsonProperty("category_name")
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public TermCategory withCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    @JsonProperty("position_from")
    public Double getPositionFrom() {
        return positionFrom;
    }

    @JsonProperty("position_from")
    public void setPositionFrom(Double positionFrom) {
        this.positionFrom = positionFrom;
    }

    public TermCategory withPositionFrom(Double positionFrom) {
        this.positionFrom = positionFrom;
        return this;
    }

    @JsonProperty("position_to")
    public Double getPositionTo() {
        return positionTo;
    }

    @JsonProperty("position_to")
    public void setPositionTo(Double positionTo) {
        this.positionTo = positionTo;
    }

    public TermCategory withPositionTo(Double positionTo) {
        this.positionTo = positionTo;
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
        return ((((((((("TermCategory"+" [categoryName=")+ categoryName)+", positionFrom=")+ positionFrom)+", positionTo=")+ positionTo)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
