package com.ulas.service;

import com.ulas.entity.UserReview;
import com.ulas.general.BaseEntityService;
import com.ulas.repository.UserReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class UserReviewService extends BaseEntityService<UserReview, UserReviewRepository> {
    protected UserReviewService(UserReviewRepository repository) {
        super(repository);
    }
}
