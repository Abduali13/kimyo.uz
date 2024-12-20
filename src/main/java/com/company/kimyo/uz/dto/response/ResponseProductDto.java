package com.company.kimyo.uz.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseProductDto {

    private Integer prodId;

    private String prodName;

//    @NotBlank(message = "Product's description cannot be null or empty")
//    private String description;
//
//    @NotBlank(message = "Product's stock cannot be null or empty")
//    private String stock;

    private String prodColor;
    private Double prodPrice;
    private Integer prodAmount;
    private String prodType;

    private Integer categoryId;

    private ResponseCategoryDto responseCategoryDto;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
