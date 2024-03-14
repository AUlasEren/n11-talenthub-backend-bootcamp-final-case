package com.ulas.controller.contract.impl;

import com.ulas.client.RestaurantServiceClient;
import com.ulas.controller.contract.UserControllerContract;
import com.ulas.dto.RestaurantDTO;
import com.ulas.dto.RestaurantResponse;
import com.ulas.dto.UserDTO;
import com.ulas.entity.User;
import com.ulas.exception.EErrorType;
import com.ulas.exception.UserManagerException;
import com.ulas.mapper.UserMapper;
import com.ulas.repository.UserRepository;
import com.ulas.request.UserSaveRequest;
import com.ulas.request.UserUpdateRequest;
import com.ulas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserControllerContractImpl implements UserControllerContract {
    private  final UserService userService;
    private final UserRepository userRepository;
    private final RestaurantServiceClient restaurantServiceClient;
    @Override
    public UserDTO saveUser(UserSaveRequest request) {
        User user = UserMapper.INSTANCE.convertToUser(request);
        user = userService.save(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO delete(Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        }
        userService.delete(user.get());
        return UserMapper.INSTANCE.convertToUserDTO(user.get());
    }

    @Override
    public UserDTO updateUser(UserUpdateRequest request) {
        Optional<User> user = userService.findById(request.id());
        if (user.isEmpty()) {
            throw new UserManagerException(EErrorType.USER_NOT_FOUND);
        }
        UserMapper.INSTANCE.updateUserFields(request, user.get());
        userService.save(user.get());
        return UserMapper.INSTANCE.convertToUserDTO(user.get());
    }
    @Override
    public List<RestaurantDTO> getRestaurantRecommendations(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserManagerException(EErrorType.USER_NOT_FOUND));

        RestaurantResponse response = restaurantServiceClient.getAllCategories();
        List<RestaurantDTO> allRestaurants = response.getData();

        List<RestaurantDTO> recommendedRestaurants = allRestaurants.stream()
                .filter(restaurant -> calculateDistance(user.getLatitude(), user.getLongitude(),
                        restaurant.latitude(), restaurant.longitude()) <= 40)
                .sorted((r1, r2) -> compareRestaurants(r1, r2, user.getLatitude(), user.getLongitude()))
                .limit(3)
                .collect(Collectors.toList());

        if (recommendedRestaurants.isEmpty()) {
            throw new UserManagerException(EErrorType.RESTAURANT_NOT_FOUND);
        }

        return recommendedRestaurants;
    }

    @Override
    public double calculateDistance(double userLat, double userLon, double restaurantLat, double restaurantLon) {
        return Math.sqrt(Math.pow(restaurantLat - userLat, 2) + Math.pow(restaurantLon - userLon, 2));
    }

    @Override
    public int compareRestaurants(RestaurantDTO r1, RestaurantDTO r2, double userLat, double userLon) {
        double score1 = r1.averageRating() * 0.7 + (1 - (calculateDistance(userLat, userLon, r1.latitude(), r1.longitude()) / 10)) * 0.3;
        double score2 = r2.averageRating() * 0.7 + (1 - (calculateDistance(userLat, userLon, r2.latitude(), r2.longitude()) / 10)) * 0.3;
        return Double.compare(score2, score1);
    }

}
