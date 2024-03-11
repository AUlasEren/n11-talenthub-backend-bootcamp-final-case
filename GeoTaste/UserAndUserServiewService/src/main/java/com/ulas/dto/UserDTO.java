package com.ulas.dto;


import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record UserDTO(@NotNull Long id,
                      @NotNull String name,
                      @NotNull String surname,
                      LocalDate birthDate,
                      @NotNull String email,
                      @NotNull String password,
                       Long userReviewId,
                        Double latitude,
                        Double longitude) {
}
