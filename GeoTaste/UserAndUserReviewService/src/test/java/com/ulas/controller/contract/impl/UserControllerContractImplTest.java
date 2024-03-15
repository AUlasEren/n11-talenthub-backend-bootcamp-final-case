package com.ulas.controller.contract.impl;


import com.ulas.client.RestaurantServiceClient;

import com.ulas.dto.RestaurantDTO;
import com.ulas.dto.RestaurantResponse;
import com.ulas.dto.UserDTO;
import com.ulas.entity.User;


import com.ulas.repository.UserRepository;
import com.ulas.request.UserSaveRequest;
import com.ulas.request.UserUpdateRequest;
import com.ulas.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerContractImplTest {
    @Mock
    private UserService userService;

    @Mock
    private RestaurantServiceClient restaurantServiceClient;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserControllerContractImpl userControllerContract;
    @Test
    void shouldSaveUserAndReturnUserDto() {

        UserSaveRequest saveRequest = new UserSaveRequest(
                "John", "Doe", LocalDate.of(1990, 1, 1), "password123", "john.doe@example.com", 40.712776, -74.005974, 1L
        );


        User savedUser = new User(
                1L, "John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com", "password123", 1L, 40.712776, -74.005974
        );


        UserDTO expectedDto = new UserDTO(
                1L, "John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com", "password123", 1L, 40.712776, -74.005974
        );


        when(userService.save(any(User.class))).thenReturn(savedUser);


        UserDTO resultDto = userControllerContract.saveUser(saveRequest);

        assertNotNull(resultDto);
        assertEquals(expectedDto.id(), resultDto.id());
        assertEquals(expectedDto.name(), resultDto.name());
        assertEquals(expectedDto.surname(), resultDto.surname());
        assertEquals(expectedDto.birthDate(), resultDto.birthDate());
        assertEquals(expectedDto.email(), resultDto.email());
        assertEquals(expectedDto.password(), resultDto.password());
        assertEquals(expectedDto.userReviewId(), resultDto.userReviewId());
        assertEquals(expectedDto.latitude(), resultDto.latitude());
        assertEquals(expectedDto.longitude(), resultDto.longitude());


        verify(userService).save(any(User.class));
    }
    @Test
    void deleteUserShouldReturnUserDto() {
        Long userId = 1L;
        User foundUser = new User(
                userId, "John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com", "password123", 1L, 40.712776, -74.005974
        );
        UserDTO expectedDto = new UserDTO(
                userId, "John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com", "password123", 1L, 40.712776, -74.005974
        );


        when(userService.findById(userId)).thenReturn(Optional.of(foundUser));
        doNothing().when(userService).delete(foundUser);
        UserDTO resultDto = userControllerContract.delete(userId);


        assertNotNull(resultDto);
        assertEquals(expectedDto.id(), resultDto.id());
        assertEquals(expectedDto.name(), resultDto.name());
        assertEquals(expectedDto.surname(), resultDto.surname());
        assertEquals(expectedDto.birthDate(), resultDto.birthDate());
        assertEquals(expectedDto.email(), resultDto.email());
        assertEquals(expectedDto.password(), resultDto.password());
        assertEquals(expectedDto.userReviewId(), resultDto.userReviewId());
        assertEquals(expectedDto.latitude(), resultDto.latitude());
        assertEquals(expectedDto.longitude(), resultDto.longitude());


        verify(userService).findById(userId);
        verify(userService).delete(foundUser);
    }
    @Test
    void updateUserShouldReturnUpdatedUserDto() {

        Long userId = 1L;
        UserUpdateRequest updateRequest = new UserUpdateRequest(
                userId, "Updated Name", "Updated Surname", LocalDate.of(1991, 1, 1), "updatedPassword123",40.7128, -74.0060, "updated.email@example.com", 2L
        );

        User foundUser = new User(
                userId, "Original Name", "Original Surname", LocalDate.of(1990, 1, 1), "original.email@example.com", "originalPassword123", 1L, 40.712776, -74.005974
        );

        UserDTO expectedUpdatedUserDto = new UserDTO(
                userId, "Updated Name", "Updated Surname", LocalDate.of(1991, 1, 1), "updated.email@example.com", "updatedPassword123", 2L, 40.7128, -74.0060
        );

        when(userService.findById(updateRequest.id())).thenReturn(Optional.of(foundUser));
        when(userService.save(any(User.class))).thenReturn(foundUser); // Bu satır güncellendi.




        UserDTO resultDto = userControllerContract.updateUser(updateRequest);

        // Sonuçların doğrulanması
        assertNotNull(resultDto);
        assertEquals(expectedUpdatedUserDto.id(), resultDto.id());
        assertEquals(expectedUpdatedUserDto.name(), resultDto.name());
        assertEquals(expectedUpdatedUserDto.surname(), resultDto.surname());
        assertEquals(expectedUpdatedUserDto.birthDate(), resultDto.birthDate());
        assertEquals(expectedUpdatedUserDto.email(), resultDto.email());
        assertEquals(expectedUpdatedUserDto.password(), resultDto.password());
        assertEquals(expectedUpdatedUserDto.userReviewId(), resultDto.userReviewId());
        assertEquals(expectedUpdatedUserDto.latitude(), resultDto.latitude());
        assertEquals(expectedUpdatedUserDto.longitude(), resultDto.longitude());


        verify(userService).findById(updateRequest.id());
        verify(userService).save(any(User.class));

    }
   /** @Test
    void getRestaurantRecommendationsShouldReturnList() {

        Long userId = 1L;
        User user = new User(
                userId, "Original Name", "Original Surname", LocalDate.of(1990, 1, 1), "original.email@example.com", "originalPassword123", 1L, 40.712776, 74.005974
        );
        RestaurantDTO restaurant1 = new RestaurantDTO("Restaurant1","Kukla kebap",74.982,22.123,123,4.0);
        RestaurantDTO restaurant2 = new RestaurantDTO("Restaurant2", "mezzaluna", 74.008,12.213,241,2.0);
        RestaurantResponse response = new RestaurantResponse();
        response.getData().add(restaurant1);
        response.getData().add(restaurant2);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(restaurantServiceClient.getAllCategories()).thenReturn(response);


        List<RestaurantDTO> recommendations = userControllerContract.getRestaurantRecommendations(userId);


        assertFalse(recommendations.isEmpty());
        assertTrue(recommendations.size() <= 3);


        verify(userRepository).findById(userId);
        verify(restaurantServiceClient).getAllCategories();
    }
**/

}
