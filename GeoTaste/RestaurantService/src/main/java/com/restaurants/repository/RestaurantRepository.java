package com.restaurants.repository;

import com.restaurants.entity.Restaurant;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends SolrCrudRepository<Restaurant,String> {
}
