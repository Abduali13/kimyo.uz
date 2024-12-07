package com.company.kimyo.uz.exception;


import com.company.kimyo.uz.dto.ErrorDto;
import com.company.kimyo.uz.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseDto<List<ErrorDto>>> methodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.badRequest().body(ResponseDto.<List<ErrorDto>>builder()
                .code(-3)
                .message("Validation error")
                .errorList(
                        e.getFieldErrors().stream()
                                .map(fieldError -> {
                                    String field = fieldError.getField();
                                    String rejectedValue = String.valueOf(fieldError.getRejectedValue());
                                    String message = fieldError.getDefaultMessage();
                                    return new ErrorDto(field, String.format("Message: %s, Rejection value: %s", message, rejectedValue));
                                }).toList()
                )
                .build());




        /*List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErrorDto> list = fieldErrors.stream()
                .map(fieldError -> {
                    String field = fieldError.getField();
                    String rejectedValue = String.valueOf(fieldError.getRejectedValue());
                    String message = fieldError.getDefaultMessage();

                    return new ErrorDto(field, String.format("Message: %s, Rejection value: %s", message, rejectedValue));
                }).toList();*/


    }
}
