package com.ulas.entity;

import com.ulas.enums.EnumRating;
import com.ulas.general.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_REVIEW")
@Getter
@Setter
public class UserReview extends BaseEntity {
    @Id
    @SequenceGenerator(name = "USER_REVIEW", sequenceName = "USER_REVIEW_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_review_generator")
    private Long id;
    @Column(name = "USER_ID",nullable = false)
    private Long userId;
    @Column(name = "RESTAURANT_ID",nullable = false)
    private String restaurantId;
    @Column(name = "REVIEW_TEXT", length = 400,nullable = false)
    private String reviewText;
    @Column(name = "REVIEW_DATE")
    private LocalDateTime reviewDate;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "RATING", nullable = false)
    private EnumRating enumRating;
}
