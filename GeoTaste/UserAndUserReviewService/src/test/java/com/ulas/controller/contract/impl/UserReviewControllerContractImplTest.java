package com.ulas.controller.contract.impl;


import com.ulas.dto.UserReviewDTO;
import com.ulas.entity.UserReview;
import com.ulas.enums.EnumRating;

import com.ulas.mapper.UserReviewMapper;
import com.ulas.request.UserReviewRequest;

import com.ulas.request.UserReviewUpdateRequest;
import com.ulas.service.UserReviewService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.Optional;
;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserReviewControllerContractImplTest {

    @Mock
    private UserReviewService userReviewService;

    @Mock
    private UserReviewMapper userReviewMapper;

    private UserReviewControllerContractImpl userReviewControllerContract;

    @BeforeEach
    void setUp() {
        userReviewControllerContract = new UserReviewControllerContractImpl(userReviewService);
    }

    @Test
    void shouldSaveUserReviewTest() {
        LocalDateTime testDate = LocalDateTime.of(2025, 3, 15, 14, 30);
        UserReviewRequest request = new UserReviewRequest(1L, "2", "Çok güzel", testDate, EnumRating.FIVE);
        UserReview userReview = new UserReview(1L, 1L, "2", "Çok güzel", testDate, EnumRating.FIVE);
        UserReviewDTO expectedUserReviewDTO = new UserReviewDTO(1L, 1L, "2", "Çok güzel", testDate, EnumRating.FIVE);


        when(userReviewService.save(any(UserReview.class))).thenReturn(userReview);


        UserReviewDTO resultDTO = userReviewControllerContract.saveUserReview(request);


        assertNotNull(resultDTO);
        assertEquals(expectedUserReviewDTO.userId(), resultDTO.userId());
        assertEquals(expectedUserReviewDTO.reviewText(), resultDTO.reviewText());
        assertEquals(expectedUserReviewDTO.reviewDate(), resultDTO.reviewDate());
        assertEquals(expectedUserReviewDTO.enumRating(), resultDTO.enumRating());


        verify(userReviewService).save(any(UserReview.class));
    }


    @Test
    void deleteReviewShouldReturnDtoOfDeletedReviewWhenReviewExists() {
        Long reviewId = 1L;
        LocalDateTime testDate = LocalDateTime.now();
        UserReview userReview = new UserReview(reviewId, 1L, "2", "Çok güzel", testDate, EnumRating.FIVE);
        when(userReviewService.findByIdWithControl(reviewId)).thenReturn(userReview);
        doNothing().when(userReviewService).delete(userReview);
        UserReviewDTO actualDto = userReviewControllerContract.deleteReview(reviewId);
        assertNotNull(actualDto);
        assertEquals(userReview.getId(), actualDto.id());
        assertEquals(userReview.getReviewText(), actualDto.reviewText());



        verify(userReviewService).findByIdWithControl(reviewId);
        verify(userReviewService).delete(userReview);
    }
    @Test
    void updateUserReviewShouldReturnUpdatedReviewDtoWhenReviewExists() {
        Long reviewId = 1L;

        UserReviewUpdateRequest updateRequest = new UserReviewUpdateRequest("Güncellenmiş çok güzel bir yorum", EnumRating.FIVE);

        LocalDateTime testDate = LocalDateTime.now();
        UserReview existingReview = new UserReview(reviewId, 1L, "2", "Eski yorum", testDate, EnumRating.THREE);
        UserReviewDTO expectedDto = new UserReviewDTO(reviewId, 1L, "2", "Güncellenmiş çok güzel bir yorum", testDate, EnumRating.FIVE);

        when(userReviewService.findById(reviewId)).thenReturn(Optional.of(existingReview));

        when(userReviewService.save(existingReview)).thenReturn(existingReview);



        UserReviewDTO actualDto = userReviewControllerContract.updateUserReview(updateRequest, reviewId);

        assertNotNull(actualDto);
        assertEquals(expectedDto.id(), actualDto.id());
        assertEquals(expectedDto.reviewText(), actualDto.reviewText());
        assertEquals(expectedDto.enumRating(), actualDto.enumRating());

        verify(userReviewService).findById(reviewId);
        verify(userReviewService).save(existingReview);
    }






}
