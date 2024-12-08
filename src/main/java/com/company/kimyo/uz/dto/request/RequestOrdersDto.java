package com.company.kimyo.uz.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrdersDto {

    @NotNull(message = "Order date cannot be null or empty")
    private LocalDate orderDate;

    @NotNull(message = "Order total price cannot be null or empty")
    private Double totalPrice;

    @NotNull(message = "Order's user id cannot be null or empty")
    private Integer userId;
}
