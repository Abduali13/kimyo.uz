package com.company.kimyo.uz.service;

import com.company.kimyo.uz.dto.ResponseDto;
import com.company.kimyo.uz.dto.request.RequestUserDto;
import com.company.kimyo.uz.dto.response.ResponseUserDto;
import com.company.kimyo.uz.entity.User;
import com.company.kimyo.uz.repository.UserRepository;
import com.company.kimyo.uz.service.mapper.UserMapper;
import com.company.kimyo.uz.service.validation.UserValidation;
import com.company.kimyo.uz.util.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements SimpleCrud<Integer, ResponseUserDto, RequestUserDto> {


    private final UserMapper userMapper;
    private final UserValidation userValidation;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<ResponseUserDto> createEntity(RequestUserDto dto) {

//        List<ErrorDto> errors = this.userValidation.userValid(dto);
//        if (!errors.isEmpty()){
//            return ResponseDto.<ResponseUserDto>builder()
//                    .code(-3)
//                    .message("Validation error")
//                    .errorList(errors).build();
//        }

        try {
            User entity = this.userMapper.toEntity(dto);
//            entity.setCreatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseUserDto>builder()
                    .success(true)
                    .message("OK")
                    .data(
                            this.userMapper.toDto(
                                    this.userRepository.save(entity)
                            )
                    )
                    .build();

        } catch (Exception e) {
            return ResponseDto.<ResponseUserDto>builder()
                    .code(-2)
                    .message(String.format("Error while saving user; message: %s", e.getMessage()
                            )
                    )
                    .build();
        }
    }

    @Override
    public ResponseDto<ResponseUserDto> getEntity(Integer entityId) {
        Optional<User> optionalUser = this.userRepository.findUserByUserIdAndDeletedAtIsNull(entityId);
        if (optionalUser.isEmpty()) {
            return ResponseDto.<ResponseUserDto>builder()
                    .code(-1)
                    .message(String.format("User with this %d id is not found", entityId)
                    )
                    .build();
        }
        return ResponseDto.<ResponseUserDto>builder()
                .success(true)
                .message("OK")
                .data(
                        this.userMapper.toDtoWithCard(
                                optionalUser.get()
                        )
                )
                .build();

    }

    @Override
    public ResponseDto<ResponseUserDto> updateEntity(Integer entityid, RequestUserDto dto) {
        Optional<User> optionalUser = this.userRepository.findUserByUserIdAndDeletedAtIsNull(entityid);
        if (optionalUser.isEmpty()) {
            return ResponseDto.<ResponseUserDto>builder()
                    .code(-1)
                    .message(String.format("User with this %d id is not found", entityid)
                    )
                    .build();
        }
        User user = optionalUser.get();
        user.setUpdatedAt(LocalDateTime.now());
        return ResponseDto.<ResponseUserDto>builder()
                .success(true)
                .message("OK")
                .data(
                        this.userMapper.toDto(
                                this.userRepository.save(
                                        this.userMapper.updateUser(dto, user)
                                )
                        )
                )
                .build();

    }

    @Override
    public ResponseDto<ResponseUserDto> deleteEntity(Integer entityid) {
        Optional<User> optionalUser = this.userRepository.findUserByUserIdAndDeletedAtIsNull(entityid);
        if (optionalUser.isEmpty()) {
            return ResponseDto.<ResponseUserDto>builder()
                    .code(-1)
                    .message(String.format("User with this %d id is not found", entityid)
                    )
                    .build();
        }
        User user = optionalUser.get();
        user.setDeletedAt(LocalDateTime.now());
        return ResponseDto.<ResponseUserDto>builder()
                .success(true)
                .message("OK")
                .data(
                        this.userMapper.toDto(
                                this.userRepository.save(user)
                        )
                )
                .build();

    }

    public ResponseDto<List<ResponseUserDto>> getAllUser() {
        List<User> users = this.userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseDto.<List<ResponseUserDto>>builder()
                    .code(-1)
                    .message("Users are not found")
                    .build();
        }
        return ResponseDto.<List<ResponseUserDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        users.stream().map(this.userMapper::toDto).toList()
                ).build();
    }

    public ResponseDto<Page<ResponseUserDto>> getAllUserByPage(Integer size, Integer page) {
        return getPageResponseDto(this.userRepository.findAll(PageRequest.of(page, size)));
    }

    public ResponseDto<Page<ResponseUserDto>> getAllUserSortedByColumn(Integer size, Integer page, String column) {
        return getPageResponseDto(this.userRepository.findAll(PageRequest.of(page, size, Sort.by(column).ascending())));
    }

    private ResponseDto<Page<ResponseUserDto>> getPageResponseDto(Page<User> userPage) {
        if (userPage.isEmpty()) {
            return ResponseDto.<Page<ResponseUserDto>>builder()
                    .code(-1)
                    .message("Users are not found")
                    .build();
        }
        return ResponseDto.<Page<ResponseUserDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        userPage.map(this.userMapper::toDto)
                ).build();
    }

    public ResponseDto<Map<String, List<ResponseUserDto>>> getAllUsersByCategory() {
        return ResponseDto.<Map<String, List<ResponseUserDto>>>builder()
                .success(true)
                .message("OK")
                .data(
                        this.userRepository.findAll()
                                .stream()
                                .map(this.userMapper::toDto)
                                .collect(Collectors.groupingBy(ResponseUserDto::getFirstname))
                ).build();
    }

    public ResponseDto<Page<ResponseUserDto>> userBasicSearch(Map<String, String> params) {
        int size = 10, page=0;
        if (params.containsKey("size")){
            size = Integer.parseInt(params.get("size"));
        }
        if (params.containsKey("page")){
            page = Integer.parseInt("page");
        }
        return ResponseDto.<Page<ResponseUserDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        this.userRepository.searchAllUserWithMoreParams(
                                params.get("id") == null ? null : Integer.parseInt(params.get("id")),
                                params.get("firstname"),
                                params.get("lastname"),
                                params.get("email"),
                                params.get("password"),
                                params.get("age") == null ? null : Integer.parseInt(params.get("age")),
                                PageRequest.of(page, size)
                        ).map(this.userMapper::toDto)
                ).build();
    }


//    public ResponseDto<List<ResponseUserDto>> getAll(){
//        List<User> list = this.userRepository.findAllByDeletedAtIsNot();
//
//        List<ResponseUserDto> list1 = list.stream()
//                .filter(user -> user.getAge() > 18).map(this.userMapper::toDto).
//                toList();
//
//
//        return ResponseDto.<List<ResponseUserDto>>builder()
//                .success(true)
//                .message("OK")
//                .data(list.stream()
//                        .filter(user -> user.getAge() > 18).map(this.userMapper::toDto).
//                        toList()).build();
//    }


}
