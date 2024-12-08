package com.company.kimyo.uz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCardDto {

    @NotBlank(message = "Card user's full name cannot be null or empty")
    private String cardFullName;

    @NotBlank(message = "Card name cannot be null or empty")
    private String cardName;

    @NotNull(message = "User id of card owner cannot be null or empty")
    private Integer userId;

    @NotBlank(message = "Card code cannot be null or empty")
    private String cardCode;

}
