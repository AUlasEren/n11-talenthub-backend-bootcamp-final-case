package com.restaurants.controller;

import com.restaurants.controller.contract.RestaurantControllerContract;
import com.restaurants.dto.RestaurantDTO;
import com.restaurants.general.RestResponse;
import com.restaurants.request.RestaurantUpdateRequest;
import com.restaurants.request.RestaurantSaveRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/restaurants")
public class RestaurantController {
    private final RestaurantControllerContract restaurantControllerContract;

    public RestaurantController(RestaurantControllerContract restaurantControllerContract) {
        this.restaurantControllerContract = restaurantControllerContract;
    }
    @PostMapping
    public ResponseEntity<RestResponse<RestaurantDTO>> saveRestaurant(@RequestBody RestaurantSaveRequest request){
        RestaurantDTO restaurantDTO = restaurantControllerContract.save(request);
        return  ResponseEntity.ok(RestResponse.of(restaurantDTO).message("Restaurant save successfully"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantDTO>> deleteRestaurant(@PathVariable String id) {
        RestaurantDTO userDTO = restaurantControllerContract.delete(id);
        return ResponseEntity.ok(RestResponse.of(userDTO).message("Restaurant deleted successfully."));
    }
    @PutMapping("/update_restaurant")
    public ResponseEntity<RestResponse<RestaurantDTO>> updateUser(@RequestBody RestaurantUpdateRequest request ){
        RestaurantDTO restaurantDTO = restaurantControllerContract.updateRestaurant(request);
        return  ResponseEntity.ok(RestResponse.of(restaurantDTO).message("Restaurant update successfully"));
    }
    @GetMapping
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> getAllCategories() {
        List<RestaurantDTO> allCategories = restaurantControllerContract.getAllRestaurants();
        return ResponseEntity.ok(RestResponse.of(allCategories).message("All categories retrieved successfully."));
    }
}
