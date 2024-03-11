package com.restaurants.mapper;

import com.restaurants.dto.RestaurantDTO;
import com.restaurants.request.RestaurantUpdateRequest;
import com.restaurants.entity.Restaurant;
import com.restaurants.request.RestaurantSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);
    Restaurant convertToRestaurant(RestaurantSaveRequest request);
    RestaurantDTO convertToRestaurantDTO(Restaurant restaurant);
    @Mapping(target = "name", source = "name")
    @Mapping(target = "latitude", source = "latitude")
    @Mapping(target = "longitude", source = "longitude")
    @Mapping(target = "id", ignore = true)
    Restaurant updateRestaurantFields(RestaurantUpdateRequest request, @MappingTarget Restaurant restaurant);

    List<RestaurantDTO> convertToRestaurantDTOs(Iterable<Restaurant> restaurantsList);
}
