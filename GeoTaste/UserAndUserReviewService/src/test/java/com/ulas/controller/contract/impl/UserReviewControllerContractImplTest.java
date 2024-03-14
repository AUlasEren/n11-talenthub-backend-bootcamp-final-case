package com.ulas.controller.contract.impl;

import com.ulas.dto.UserReviewDTO;
import com.ulas.entity.UserReview;
import com.ulas.enums.EnumRating;
import com.ulas.mapper.UserReviewMapper;
import com.ulas.request.UserReviewRequest;
import com.ulas.request.UserReviewUpdateRequest;
import com.ulas.service.UserReviewService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserReviewControllerContractImplTest {
        @Mock
    private UserReviewService userReviewService;
    @Mock
    private UserReviewMapper userReviewMapper;

    @InjectMocks
    private UserReviewControllerContractImpl userReviewController;

    @Test
    void saveUserReviewTest() {
       UserReviewRequest request = new UserReviewRequest(1L,"2","Çok güzel", LocalDateTime.now(), EnumRating.FIVE);

        UserReview userReview = new UserReview(1L,1L,"2","Çok güzel", LocalDateTime.now(),EnumRating.FIVE);


        UserReviewDTO userReviewDTO = new UserReviewDTO(1L,1L,"2","Çok güzel", LocalDateTime.now(),EnumRating.FIVE);


        when(userReviewMapper.convertToUserReview(any(UserReviewRequest.class))).thenReturn(userReview);
        when(userReviewService.save(any(UserReview.class))).thenReturn(userReview);
        when(userReviewMapper.convertToUserReviewDTO(any(UserReview.class))).thenReturn(userReviewDTO);

        UserReviewDTO result = userReviewController.saveUserReview(request);
        assertNotNull(result);
        verify(userReviewService, times(1)).save(userReview);}

    @Test
    void deleteReviewTest() {
        Long reviewId = 1L;
       UserReview userReview = new UserReview();


        UserReviewDTO userReviewDTO = new UserReviewDTO(
                1L,
                1L,
                "restaurantId",
                "Yemekler harika!",
                LocalDateTime.now(),
              EnumRating.FIVE
        );

        when(userReviewService.findByIdWithControl(reviewId)).thenReturn(userReview);
       when(userReviewMapper.convertToUserReviewDTO(any(UserReview.class))).thenReturn(userReviewDTO);

       UserReviewDTO result = userReviewController.deleteReview(reviewId);

        assertNotNull(result);
        verify(userReviewService, times(1)).delete(userReview);
    }

    @Test
    void updateUserReviewTest() {
       // Test için gerekli nesnelerin oluşturulması
        Long reviewId = 1L;
        UserReviewUpdateRequest updateRequest = new UserReviewUpdateRequest(
                "Yemekler mükemmeldi!", // reviewText
                EnumRating.FOUR // enumRating, EnumRating.FOUR varsayıyorum ki
        );

        UserReview userReview = new UserReview();
        // userReview detayları buraya gelecek, örnek bir ID ataması yaptım
        userReview.setId(reviewId);
       userReview.setReviewText("Eski yorum metni");
        userReview.setEnumRating(EnumRating.FIVE);

        UserReviewDTO userReviewDTO = new UserReviewDTO(
                reviewId, // id
                1L, // userId
                "restaurantId", // restaurantId
                updateRequest.reviewText(), // reviewText
                LocalDateTime.now(), // reviewDate
                updateRequest.enumRating() // enumRating
       );

       // userReviewMapper'ın updateUserReviewFields metodunun mocklanması
        doAnswer(invocation -> {
           UserReview ur = invocation.getArgument(0);
            UserReviewUpdateRequest urRequest = invocation.getArgument(1);

            ur.setReviewText(urRequest.reviewText());
           ur.setEnumRating(urRequest.enumRating());

            return null; // void metodlar için return null kullanılır, bu satır opsiyoneldir
       }).when(userReviewMapper).updateUserReviewFields(any(UserReview.class), any(UserReviewUpdateRequest.class));

        // userReviewService ve userReviewMapper metodlarının mocklanması
        when(userReviewService.findById(reviewId)).thenReturn(Optional.of(userReview));
        when(userReviewService.save(any(UserReview.class))).thenReturn(userReview);
      when(userReviewMapper.convertToUserReviewDTO(any(UserReview.class))).thenReturn(userReviewDTO);

       // updateUserReview metodu çağrıldığında testin yapılması
        UserReviewDTO result = userReviewController.updateUserReview(updateRequest, reviewId);
       // Sonuçların doğrulanması
        assertNotNull(result);
        assertEquals(updateRequest.reviewText(), result.reviewText());

       // userReviewService ve userReviewMapper metodlarının çağrılıp çağrılmadığının doğrulanması
        verify(userReviewMapper).updateUserReviewFields(any(UserReview.class), any(UserReviewUpdateRequest.class));
        verify(userReviewService).save(any(UserReview.class));
    }
}
