package com.company.kimyo.uz.service.mapper;

import com.company.kimyo.uz.dto.request.RequestUserDto;
import com.company.kimyo.uz.dto.response.ResponseUserDto;
import com.company.kimyo.uz.entity.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class UserMapper {

    @Lazy
    @Autowired
    protected CardMapper cardMapper;

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "cards", ignore = true)
    public abstract User toEntity(RequestUserDto dto);


    @Mapping(target = "cards", ignore = true)
    public abstract ResponseUserDto toDto(User user);

    @Mapping(target = "cards", expression = "java(user.getCards().stream().map(this.cardMapper::toDto).collect(Collectors.toSet()))")
    public abstract ResponseUserDto toDtoWithCard(User user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "cards", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = User.class)
    public abstract User updateUser(RequestUserDto dto, @MappingTarget User user);

//    private abstract Set<ResponseCardDto> fromDtoToEntity(Set<Card> cards);


   /* public ResponseUserDto toDto(User user){
         return ResponseUserDto.builder()
                 .userId(user.getUserId())
                 .age(user.getAge())
                 .firstname(user.getFirstname())
                 .lastname(user.getLastname())
                 .email(user.getEmail())
                 .password(user.getPassword())
                 .createdAt(user.getCreatedAt())
                 .updatedAt(user.getUpdatedAt())
                 .deletedAt(user.getDeletedAt())
                 .build();
    }

    public ResponseUserDto toDtoWithCard(User user){
        return ResponseUserDto.builder()
                .userId(user.getUserId())
                .age(user.getAge())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .deletedAt(user.getDeletedAt())
                .cards(this.fromDtoToEntity(user.getCards()))
                .build();
    }
    public User toEntity(ResponseUserDto dto){
        return User.builder()
                .age(dto.getAge())
                .email(dto.getEmail())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .password(dto.getPassword())
                .build();
    }
    public User updateUser(ResponseUserDto dto, User user){
        if (dto.getFirstname() != null){
            user.setFirstname(dto.getFirstname());
        }
        if (dto.getLastname() != null){
            user.setLastname(dto.getLastname());
        }
        if (dto.getAge() != null){
            user.setAge(dto.getAge());
        }
        if (dto.getEmail() != null){
            user.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null){
            user.setPassword(dto.getPassword());
        }
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }


    private Set<ResponseCardDto> fromDtoToEntity(Set<Card> cards){
        Set<ResponseCardDto> cardDtos = new HashSet<>();
        for (Card card : cards) {
            cardDtos.add(this.cardMapper.toDto(card));
        }
        return cardDtos;
    }*/
}