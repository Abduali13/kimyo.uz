package com.company.kimyo.uz.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserDto {

    @NotNull(message = "Firstname cannot be null")
    @NotEmpty(message = "Firstname cannot be empty")
    private String firstname;

    @NotBlank(message = "Lastname cannot be null or empty")
    private String lastname;

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email cannot be null or empty")
    private String email;

    @NotBlank(message = "Password cannot be null or empty")
    @Size(min = 8, max = 16, message = "Incorrect password size")
    private String password;

    @Max(value = 100, message = "Age must be less than 100")
    @Min(value = 16, message = "Age must be more than 16")
    @NotNull(message = "Age cannot be null")
    private Integer age;
}
