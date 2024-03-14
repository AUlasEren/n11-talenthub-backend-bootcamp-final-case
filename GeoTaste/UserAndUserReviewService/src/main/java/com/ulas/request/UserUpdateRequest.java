package com.ulas.request;




import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public record UserUpdateRequest (   @NotNull(message = "Id cannot be null")
                                    Long id,
                                    @NotBlank(message = "Name cannot be empty")
                                    @Length(min = 1, max = 100)
                                    String name,
                                    @NotBlank(message = "Surname cannot be empty")
                                    @Length(min = 1, max = 100)
                                    String surname,
                                    @Past
                                    LocalDate birthDate,
                                    @NotBlank(message = "Password cannot be empty")
                                    @Length(min = 1, max = 100)
                                    String password,
                                    Double latitude,
                                    Double longitude,
                                    @NotBlank(message = "Email cannot be empty")
                                    String email,
                                    @NotNull(message = "UserId cannot be null")
                                    Long userReviewId ){
}
