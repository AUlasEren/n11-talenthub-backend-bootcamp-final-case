package com.ulas.controller;


import com.ulas.controller.contract.UserControllerContract;
import com.ulas.dto.RestaurantDTO;

import com.ulas.dto.UserDTO;

import com.ulas.general.RestResponse;
import com.ulas.request.UserSaveRequest;
import com.ulas.request.UserUpdateRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor

public class UserController {
    private final UserControllerContract userControllerContract;


    @PostMapping
    public ResponseEntity<RestResponse<UserDTO>> saveUser(@Valid @RequestBody  UserSaveRequest request){
        UserDTO userDTO = userControllerContract.saveUser(request);

        return ResponseEntity.ok(RestResponse.of(userDTO).message("User saved successfully."));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<UserDTO>> deleteUser(@PathVariable Long id) {
        UserDTO userDTO = userControllerContract.delete(id);
        return ResponseEntity.ok(RestResponse.of(userDTO).message("User deleted successfully."));
    }
    @PutMapping("/update_user")
    public ResponseEntity<RestResponse<UserDTO>> updateUser(@RequestBody UserUpdateRequest request ){
       UserDTO userDTO = userControllerContract.updateUser(request);
       return  ResponseEntity.ok(RestResponse.of(userDTO).message("User update successfully"));
    }
    @GetMapping("/{userId}/restaurant-recommendations")
    public ResponseEntity<RestResponse<List<RestaurantDTO>>> getRestaurantRecommendations(@PathVariable Long userId) {
        List<RestaurantDTO> recommendations = userControllerContract.getRestaurantRecommendations(userId);
        return ResponseEntity.ok(RestResponse.of(recommendations).message("Restaurant recommendations retrieved successfully."));
    }
}
