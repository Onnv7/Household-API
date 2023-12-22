package com.onnv.household.exception;

import com.onnv.household.constants.ErrorConstant;
import com.onnv.household.model.CustomException;
import com.onnv.household.model.ErrorResponse;
import com.onnv.household.model.FieldError;
import jakarta.servlet.ServletException;
import jakarta.validation.ConstraintViolationException;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;


@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    private static final List<String> error400= Arrays.asList(
    );
    private static final List<String> error404= Arrays.asList(
    );
    private static final List<String> error403= Arrays.asList(
    );
    private static final List<String> error401= Arrays.asList(
    );

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ex.printStackTrace();

        ErrorResponse res = ErrorResponse.builder()
                .message(ErrorConstant.REQUEST_BODY_INVALID)
                .stack(Arrays.toString(ex.getStackTrace()))
                .build();
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        ErrorResponse res = ErrorResponse.builder()
                .message(ex.getMessage())
                .stack(Arrays.toString(ex.getStackTrace()))
                .build();
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ex.printStackTrace();
        ErrorResponse res = ErrorResponse.builder()
                .message(ex.getMessage())
                .stack(Arrays.toString(ex.getStackTrace()))
                .build();
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ex.printStackTrace();

        ErrorResponse res = ErrorResponse.builder()
                .message(ErrorConstant.REQUEST_BODY_INVALID)
                .details(
                        ex.getFieldErrors().stream()
                        .map(
                                it-> FieldError.builder()
                                        .field(it.getField())
                                        .valueReject(it.getRejectedValue())
                                        .validate(it.getDefaultMessage())
                                        .build()
                        ).toList()
                )
                .build();
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}