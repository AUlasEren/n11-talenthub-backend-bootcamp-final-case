package com.restaurants.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {
    INVALID_PARAMETER(1000,"Invalid parameter entered", BAD_REQUEST),
    METHOD_MIS_MATCH_ERROR(1001,"The value you entered does not match the desired value.", BAD_REQUEST),
    METHOD_NOT_VALID_ARGUMENT_ERROR(1002,"Missing parameter submission in URL",BAD_REQUEST),
    HTTP_MESSAGE_NOT_READABLE(1003,"HTTP message not readable" ,BAD_REQUEST ),
    RESTAURANT_NOT_FOUND(2001,"Restaurant not found", BAD_REQUEST),
    NOT_DECODED(1005,"Token can not decoded", INTERNAL_SERVER_ERROR),
    UNEXPECTED_ERROR(1008,"Unexpected Error Occured", INTERNAL_SERVER_ERROR);





    private int code;
    private String message;
    private HttpStatus httpStatus;
}
