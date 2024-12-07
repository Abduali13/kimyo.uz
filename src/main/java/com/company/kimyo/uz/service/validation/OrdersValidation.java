package com.company.kimyo.uz.service.validation;

import com.company.kimyo.uz.dto.ErrorDto;
import com.company.kimyo.uz.dto.response.ResponseOrdersDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
@Component
public class OrdersValidation {
    public List<ErrorDto> ordersValid(ResponseOrdersDto dto){
        List<ErrorDto> errorList = new ArrayList<>();
        if (StringUtils.isBlank(dto.getOrderDate().toString())){
            errorList.add(new ErrorDto("ordersDate", "Order's date cannot be null"));
        } else if (dto.getTotalPrice()==null) {
            errorList.add(new ErrorDto("order total price", "Order's total price cannot be null"));
        }
        return errorList;
    }
}
