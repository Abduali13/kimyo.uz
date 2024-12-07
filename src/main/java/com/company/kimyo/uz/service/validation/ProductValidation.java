package com.company.kimyo.uz.service.validation;

import com.company.kimyo.uz.dto.ErrorDto;
import com.company.kimyo.uz.dto.response.ResponseProductDto;
import com.company.kimyo.uz.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductValidation {

    private final CategoryRepository categoryRepository;
    public List<ErrorDto> productValid(ResponseProductDto dto){
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(dto.getCategoryId()).isEmpty()){
            errorList.add(new ErrorDto("categoryId", String.format("Category with this %d is not found", dto.getCategoryId())));
        }
        return errorList;
    }
}
