package com.ulas.dto;

import com.ulas.enums.EnumRating;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record UserReviewDTO( @NotNull Long id,
                             @NotNull Long userId,
                              String restaurantId,
                             @NotBlank @Length(min = 1, max = 400) String reviewText,
                             LocalDateTime reviewDate,
                             EnumRating enumRating) {
}
