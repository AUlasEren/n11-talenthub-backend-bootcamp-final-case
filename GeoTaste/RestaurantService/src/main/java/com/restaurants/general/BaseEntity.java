package com.restaurants.general;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;


@Getter
@Setter
@SolrDocument(solrCoreName = "base_entity")
public abstract class BaseEntity implements BaseModel {

    @Transient
    @JsonIgnore
    private BaseAdditionalFields baseAdditionalFields;
    @Indexed(name = "base_additional_fields", type = "string")
    private String baseAdditionalFieldsJson;
    public void setBaseAdditionalFields(BaseAdditionalFields baseAdditionalFields) {
        this.baseAdditionalFields = baseAdditionalFields;
        if (baseAdditionalFields != null) {

            ObjectMapper mapper = new ObjectMapper();
            try {
                this.baseAdditionalFieldsJson = mapper.writeValueAsString(baseAdditionalFields);
            } catch (Exception e) {

                this.baseAdditionalFieldsJson = null;
            }
        }
    }
    public BaseAdditionalFields getBaseAdditionalFields() {
        if (this.baseAdditionalFields == null && this.baseAdditionalFieldsJson != null) {
            // JSON string'inden BaseAdditionalFields nesnesini dönüştürme
            ObjectMapper mapper = new ObjectMapper();
            try {
                this.baseAdditionalFields = mapper.readValue(this.baseAdditionalFieldsJson, BaseAdditionalFields.class);
            } catch (Exception e) {
                // Log the exception or handle it
                this.baseAdditionalFields = null;
            }
        }
        return this.baseAdditionalFields;
    }
}
