package com.ulas.controller.contract.impl;

import com.ulas.client.RestaurantServiceClient;
import com.ulas.controller.contract.UserReviewControllerContract;
import com.ulas.dto.RestaurantDTO;
import com.ulas.dto.UserReviewDTO;
import com.ulas.entity.User;
import com.ulas.entity.UserReview;
import com.ulas.exception.EErrorType;
import com.ulas.exception.UserManagerException;
import com.ulas.general.KafkaProducerService;
import com.ulas.mapper.UserReviewMapper;
import com.ulas.repository.UserRepository;
import com.ulas.request.UserReviewRequest;
import com.ulas.request.UserReviewUpdateRequest;
import com.ulas.service.UserReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserReviewControllerContractImpl implements UserReviewControllerContract {
    private final UserReviewService userReviewService;

    @Override
    public UserReviewDTO saveUserReview(UserReviewRequest request) {
        UserReview userReview = UserReviewMapper.INSTANCE.convertToUserReview(request);
        userReview = userReviewService.save(userReview);
        return UserReviewMapper.INSTANCE.convertToUserReviewDTO(userReview);
    }

    @Override
    public UserReviewDTO deleteReview(Long id) {
        UserReview userReview = userReviewService.findByIdWithControl(id);
        if(userReview == null){
            throw new UserManagerException(EErrorType.USER_REVIEW_NOT_FOUND);
        }
        userReviewService.delete(userReview);
        return UserReviewMapper.INSTANCE.convertToUserReviewDTO(userReview);
    }

    @Override
    public UserReviewDTO updateUserReview(UserReviewUpdateRequest request,Long id) {
        Optional<UserReview> userReview = userReviewService.findById(id);
        if(userReview.isEmpty()){
            throw new UserManagerException(EErrorType.USER_REVIEW_NOT_FOUND);
        }
        UserReviewMapper.INSTANCE.updateUserReviewFields(userReview.get(),request);
        userReviewService.save(userReview.get());
        return UserReviewMapper.INSTANCE.convertToUserReviewDTO(userReview.get());
    }


}

