package com.ulas.entity;

import com.ulas.general.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_ENTITY")
@Getter
@Setter
public class User extends BaseEntity {
    @SequenceGenerator(name = "USER", sequenceName = "USER_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @Id
    private Long id;
    @Column(name = "NAME", length = 100, nullable = false)
    private String name;
    @Column(name = "SURNAME", length = 100, nullable = false)
    private String surname;
    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;
    @Email
    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;
    @Column(name = "PASSWORD", length = 400, nullable = false)
    private String password;
    @Column(name = "USER_REVIEW_ID")
    @Min(value = 1, message = "userReviewId must be greater than 0")
    private Long userReviewId;
    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;
}
