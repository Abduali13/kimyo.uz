package com.company.kimyo.uz.service.validation;

import com.company.kimyo.uz.dto.response.ResponseCategoryDto;
import com.company.kimyo.uz.dto.ErrorDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryValidation {
    public List<ErrorDto> categoryValid(ResponseCategoryDto dto){
        List<ErrorDto> errorList = new ArrayList<>();
        if (StringUtils.isBlank(dto.getCategoryName())){
            errorList.add(new ErrorDto("categoryName", "Category name cannot be null or empty"));
        }
        return errorList;
    }
}
