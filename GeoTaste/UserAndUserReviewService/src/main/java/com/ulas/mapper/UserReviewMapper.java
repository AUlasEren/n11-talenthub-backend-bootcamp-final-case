package com.ulas.mapper;


import com.ulas.dto.UserReviewDTO;
import com.ulas.entity.UserReview;
import com.ulas.request.UserReviewRequest;
import com.ulas.request.UserReviewUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserReviewMapper {
    UserReviewMapper INSTANCE = Mappers.getMapper(UserReviewMapper.class);
    UserReview convertToUserReview(UserReviewRequest request);
    UserReviewDTO convertToUserReviewDTO(UserReview userReview);
    @Mapping(target = "id", ignore = true)
    void updateUserReviewFields(@MappingTarget UserReview userReview, UserReviewUpdateRequest request);

}
