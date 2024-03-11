package com.ulas.controller.contract;


import com.ulas.dto.UserReviewDTO;
import com.ulas.request.UserReviewRequest;
import com.ulas.request.UserReviewUpdateRequest;


public interface UserReviewControllerContract {
    UserReviewDTO saveUserReview(UserReviewRequest request);
    UserReviewDTO deleteReview(Long id);

    UserReviewDTO updateUserReview(UserReviewUpdateRequest request,Long id);
}
