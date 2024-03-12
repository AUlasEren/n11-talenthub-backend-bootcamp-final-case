package com.restaurants.request;

public record RestaurantSaveRequest(String name,double latitude,double longitude,double averageRating,int reviewCount) {
}
