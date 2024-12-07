package com.company.kimyo.uz.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrdersDto {
    private LocalDate orderDate;
    private Double totalPrice;
}
