package com.company.kimyo.uz.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCategoryDto {

    @NotBlank(message = "Category name cannot be null or empty")
    private String categoryName;
}
