package com.artist.blog_app.exceptions;

import com.artist.blog_app.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiResponse);
    }
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<Map<String,String>> methodArgNotValidExceptionHandler(MethodArgumentNotValidException ex){
        Map<String,String> resp = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    String fieldName = error.getField();
                    String message = error.getDefaultMessage();
                    resp.put(fieldName, message);
                });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(resp);
    }
}
