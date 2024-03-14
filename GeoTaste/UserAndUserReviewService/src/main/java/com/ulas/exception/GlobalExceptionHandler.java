package com.ulas.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import com.ulas.general.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@RestController
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    private final KafkaProducerService kafkaProducerService;

    private ErrorMessage createErrorMessage(EErrorType errorType, String message) {
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(message)
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, WebRequest request) {
        EErrorType errorType = EErrorType.UNEXPECTED_ERROR;
        String message = ex.getMessage();
        ErrorMessage errorMessage = createErrorMessage(errorType, message);
        sendLogToKafka(errorType, ex, message);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserManagerException.class)
    public ResponseEntity<ErrorMessage> handleManagerException(UserManagerException ex) {
        EErrorType errorType = ex.getErrorType();
        String message = ex.getMessage();
        ErrorMessage errorMessage = createErrorMessage(errorType, message);
        sendLogToKafka(errorType, ex, message);
        return new ResponseEntity<>(errorMessage, errorType.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        EErrorType errorType = EErrorType.INVALID_PARAMETER;
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        String message = String.join(", ", errors);
        ErrorMessage errorMessage = createErrorMessage(errorType, message);
        sendLogToKafka(errorType, ex, message);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }


    private void sendLogToKafka(EErrorType errorType, Exception ex, String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String logMessage = mapper.writeValueAsString(Map.of(
                    "code", errorType.getCode(),
                    "message", message,
                    "exception", ex.getMessage()
            ));
            kafkaProducerService.sendMessage("errorLog", logMessage);
        } catch (Exception kafkaEx) {
            log.error("Failed to send log to Kafka", kafkaEx);
        }
    }

}
