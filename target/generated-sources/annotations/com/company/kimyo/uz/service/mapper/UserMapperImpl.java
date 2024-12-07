package com.company.kimyo.uz.service.mapper;

import com.company.kimyo.uz.dto.request.RequestUserDto;
import com.company.kimyo.uz.dto.response.ResponseUserDto;
import com.company.kimyo.uz.entity.User;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-07T23:54:07+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public User toEntity(RequestUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.firstname( dto.getFirstname() );
        user.lastname( dto.getLastname() );
        user.email( dto.getEmail() );
        user.password( dto.getPassword() );
        user.age( dto.getAge() );

        return user.build();
    }

    @Override
    public ResponseUserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        ResponseUserDto.ResponseUserDtoBuilder responseUserDto = ResponseUserDto.builder();

        responseUserDto.userId( user.getUserId() );
        responseUserDto.firstname( user.getFirstname() );
        responseUserDto.lastname( user.getLastname() );
        responseUserDto.email( user.getEmail() );
        responseUserDto.password( user.getPassword() );
        responseUserDto.age( user.getAge() );
        responseUserDto.createdAt( user.getCreatedAt() );
        responseUserDto.updatedAt( user.getUpdatedAt() );
        responseUserDto.deletedAt( user.getDeletedAt() );

        return responseUserDto.build();
    }

    @Override
    public ResponseUserDto toDtoWithCard(User user) {
        if ( user == null ) {
            return null;
        }

        ResponseUserDto.ResponseUserDtoBuilder responseUserDto = ResponseUserDto.builder();

        responseUserDto.userId( user.getUserId() );
        responseUserDto.firstname( user.getFirstname() );
        responseUserDto.lastname( user.getLastname() );
        responseUserDto.email( user.getEmail() );
        responseUserDto.password( user.getPassword() );
        responseUserDto.age( user.getAge() );
        responseUserDto.createdAt( user.getCreatedAt() );
        responseUserDto.updatedAt( user.getUpdatedAt() );
        responseUserDto.deletedAt( user.getDeletedAt() );

        responseUserDto.cards( user.getCards().stream().map(this.cardMapper::toDto).collect(Collectors.toSet()) );

        return responseUserDto.build();
    }

    @Override
    public User updateUser(RequestUserDto dto, User user) {
        if ( dto == null ) {
            return user;
        }

        if ( dto.getFirstname() != null ) {
            user.setFirstname( dto.getFirstname() );
        }
        if ( dto.getLastname() != null ) {
            user.setLastname( dto.getLastname() );
        }
        if ( dto.getEmail() != null ) {
            user.setEmail( dto.getEmail() );
        }
        if ( dto.getPassword() != null ) {
            user.setPassword( dto.getPassword() );
        }
        if ( dto.getAge() != null ) {
            user.setAge( dto.getAge() );
        }

        return user;
    }
}
