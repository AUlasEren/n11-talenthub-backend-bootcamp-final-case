package com.ulas;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserAndUserReviewServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserAndUserReviewServiceApplication.class);
    }
}