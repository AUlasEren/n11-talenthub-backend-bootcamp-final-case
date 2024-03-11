package com.restaurants.request;

public record RestaurantUpdateRequest(String id,
                                      String name,
                                      double latitude,
                                      double longitude ) {
}
