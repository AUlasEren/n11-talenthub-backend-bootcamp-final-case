package com.restaurants.entity;

import com.restaurants.general.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@SolrDocument(solrCoreName = "geotaste_restaurant")
@Getter
public class Restaurant extends BaseEntity {
    @Id
    @Indexed(name = "id", type = "string")
    private String id;
    @Indexed(name = "name", type = "string")
    private String name;
    @Indexed(name = "latitude", type = "double")
    private double latitude;
    @Indexed(name = "longitude",type = "double")
    private double longitude;






}
