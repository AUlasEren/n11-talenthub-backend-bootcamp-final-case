package com.ulas.request;

import com.ulas.enums.EnumRating;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record UserReviewRequest(@NotNull(message = "User Id cannot be empty")
                                @Min(value = 1, message = "userId must be greater than 0")
                                Long userId,
                                @NotNull
                                @Min(value = 1, message = "restaurantId must be greater than 0")
                                Long restaurantId,
                                @NotBlank(message = "Review Text cannot be empty")
                                String reviewText,
                                LocalDateTime reviewDate,
                                @NotNull(message = "enumRating cannot be null")
                                EnumRating enumRating) {
}
