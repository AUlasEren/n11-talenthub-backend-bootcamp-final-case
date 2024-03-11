package com.restaurants.exception;

import lombok.Getter;

@Getter
public class RestaurantManagerException extends RuntimeException{
    private final EErrorType errorType;

    public RestaurantManagerException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public RestaurantManagerException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}