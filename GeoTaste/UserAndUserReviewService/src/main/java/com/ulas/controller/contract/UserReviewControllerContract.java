package com.ulas.controller.contract;


import com.ulas.dto.RestaurantDTO;
import com.ulas.dto.UserReviewDTO;
import com.ulas.entity.User;
import com.ulas.exception.EErrorType;
import com.ulas.exception.UserManagerException;
import com.ulas.request.UserReviewRequest;
import com.ulas.request.UserReviewUpdateRequest;

import java.util.List;
import java.util.stream.Collectors;


public interface UserReviewControllerContract {
    UserReviewDTO saveUserReview(UserReviewRequest request);
    UserReviewDTO deleteReview(Long id);

    UserReviewDTO updateUserReview(UserReviewUpdateRequest request,Long id);


}
