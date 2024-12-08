package com.company.kimyo.uz.service.validation;

import com.company.kimyo.uz.dto.ErrorDto;
import com.company.kimyo.uz.dto.request.RequestUserDto;
import com.company.kimyo.uz.dto.response.ResponseUserDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidation {
    public List<ErrorDto> userValid(RequestUserDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (StringUtils.isBlank(dto.getFirstname())) {
            errorList.add(new ErrorDto("firstname", "Firstname cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getLastname())) {
            errorList.add(new ErrorDto("lastname", "Lastname cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getEmail())) {
            errorList.add(new ErrorDto("email", "Email cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getPassword())) {
            errorList.add(new ErrorDto("password", "Password cannot be null or empty"));
        }
        if (dto.getAge()==null){
            errorList.add(new ErrorDto("age", "Age cannot be null or empty"));
        }
        return errorList;


    }
}
