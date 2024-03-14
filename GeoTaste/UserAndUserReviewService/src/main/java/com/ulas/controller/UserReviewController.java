package com.ulas.controller;

import com.ulas.controller.contract.UserReviewControllerContract;
import com.ulas.dto.UserReviewDTO;
import com.ulas.general.RestResponse;
import com.ulas.request.UserReviewRequest;
import com.ulas.request.UserReviewUpdateRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user_reviews")
@RequiredArgsConstructor
public class UserReviewController {
    private final UserReviewControllerContract userReviewControllerContract;

    @PostMapping
    public ResponseEntity<RestResponse<UserReviewDTO>> saveUser(@RequestBody @Valid UserReviewRequest request){
        UserReviewDTO userReviewDTO = userReviewControllerContract.saveUserReview(request);
        return ResponseEntity.ok(RestResponse.of(userReviewDTO).message("User Review saved successfully."));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<UserReviewDTO>> deleteUserReview(@PathVariable Long id) {
        UserReviewDTO userDTO = userReviewControllerContract.deleteReview(id);
        return ResponseEntity.ok(RestResponse.of(userDTO).message("User Review deleted successfully."));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<RestResponse<UserReviewDTO>> updateUserReview(@PathVariable Long id,@RequestBody UserReviewUpdateRequest request ){
        UserReviewDTO userDTO = userReviewControllerContract.updateUserReview(request,id);
        return  ResponseEntity.ok(RestResponse.of(userDTO).message("User Review update successfully"));
    }
}
