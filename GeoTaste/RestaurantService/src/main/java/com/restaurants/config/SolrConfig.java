package com.restaurants.config;


import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;

import org.springframework.data.solr.repository.config.EnableSolrRepositories;




@Configuration
@EnableSolrRepositories(
    basePackages =  "com.restaurants"
)
@ComponentScan
public class SolrConfig {
  @Value("${spring.data.solr.host}")
  private String solrAddress;

  @Bean
  public SolrClient solrClient(){
    System.out.println("solrraaarrrararr"+ solrAddress);
    return new HttpSolrClient.Builder(String.format("http://%s:8983/solr",solrAddress)).build();

  }


  @Bean
  public SolrTemplate solrTemplate(SolrClient solrClient){
    return new SolrTemplate(solrClient);
  }



}
