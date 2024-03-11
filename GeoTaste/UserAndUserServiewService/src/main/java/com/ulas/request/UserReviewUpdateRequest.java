package com.ulas.request;


import com.ulas.enums.EnumRating;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UserReviewUpdateRequest(@NotBlank @Length(min = 1, max = 100)String reviewText,

                                      @NotNull(message = "enumRating cannot be null")EnumRating enumRating) {

}
