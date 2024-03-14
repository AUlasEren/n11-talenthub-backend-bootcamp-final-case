package com.ulas.request;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public record UserSaveRequest(@NotBlank(message = "Name cannot be empty")
                              String name,
                              @NotBlank(message = "Surname cannot be empty")
                              String surname,
                              @Past
                              LocalDate birthDate,
                              @NotBlank(message = "Password connot be empty")
                              String password,

                              @NotBlank(message = "Email cannot be empty")
                              String email,
                              Double latitude,
                              Double longitude,

                              Long userReviewId ) {
}
