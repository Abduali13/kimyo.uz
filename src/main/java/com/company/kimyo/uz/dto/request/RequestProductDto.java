package com.company.kimyo.uz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDto {

    @NotBlank(message = "Product name cannot be null or empty")
    private String prodName;
    private String prodColor;
    private String prodType;
    private Double prodPrice;
    private Integer prodAmount;

    @NotNull(message = "Product's category id cannot be null or empty")
    private Integer categoryId;
}
