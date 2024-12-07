package com.company.kimyo.uz.service.validation;

import com.company.kimyo.uz.dto.response.ResponseCardDto;
import com.company.kimyo.uz.dto.ErrorDto;
import com.company.kimyo.uz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CardValidation {

    //private final UserService userService;
    private final UserRepository userRepository;

    public List<ErrorDto> cardValid(ResponseCardDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (this.userRepository.findUserByUserIdAndDeletedAtIsNull(dto.getUserId()).isEmpty()){
            errors.add(new ErrorDto("userId", String.format("User with %d id is not found", dto.getUserId())));
        }
        return errors;
    }
}
