package com.restaurants.general;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;


@Getter
@Setter
@SolrDocument(solrCoreName = "base_entity")
public abstract class BaseEntity implements BaseModel {
    @Indexed(name = "base_additional_fields")
    private BaseAdditionalFields baseAdditionalFields;
}
