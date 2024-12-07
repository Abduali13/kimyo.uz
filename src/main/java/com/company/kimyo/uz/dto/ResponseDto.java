package com.company.kimyo.uz.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto <T>{
    private int code;
    /*
     *  1 already exists
     *  0 it is ok         -> 202
     * -1 is not found     -> 404
     * -2 db error         -> 400
     * -3 validation error -> 400
     * -4 any exception
     * */
    private String message;
    private boolean success;
    private T data;
    private List<ErrorDto> errorList;
}
