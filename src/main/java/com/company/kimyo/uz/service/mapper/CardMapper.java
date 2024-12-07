package com.company.kimyo.uz.service.mapper;


import com.company.kimyo.uz.dto.request.RequestCardDto;
import com.company.kimyo.uz.dto.response.ResponseCardDto;
import com.company.kimyo.uz.entity.Card;
import org.mapstruct.*;

import java.util.stream.Collectors;


//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class CardMapper {

    //todo: MapStruct

    @Mapping(target = "cardId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "cardName", source = "cardName")
    @Mapping(target = "cardCode", expression = "java(\"0000\")")
    public abstract Card toEntity(RequestCardDto dto);

    @Mapping(target = "users", ignore = true)
    public abstract ResponseCardDto toDto(Card card);


    @Mapping(target = "cardId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = Card.class)
    public abstract Card updateCard(RequestCardDto dto, @MappingTarget Card card);


//    public abstract ResponseCardDto toDtoWithUser(Card card);

//    public abstract Set<ResponseCardDto> fromEntityToDto(Set<Card> cardList);










   /* public ResponseCardDto toDto(Card card) {
        return ResponseCardDto.builder()
                .cardId(card.getCardId())
                .userId(card.getUserId())
//                .cardFullName(card.getCardFullName())
                .cardName(card.getCardName())
                .cardCode(card.getCardCode())
                .createdAt(card.getCreatedAt())
                .updatedAt(card.getUpdatedAt())
                .deletedAt(card.getDeletedAt())
                .build();
    }

    public Card toEntity(ResponseCardDto dto) {
        return Card.builder()
                .cardName(dto.getCardName())
//                .cardFullName(dto.getCardFullName())
                .userId(dto.getUserId())
                .cardCode(dto.getCardCode())
                .build();
    }

    public ResponseCardDto toDtoWithUser(Card card) {
        return ResponseCardDto.builder()
                .cardId(card.getCardId())
                .cardName(card.getCardName())
//                .cardFullName(card.getCardFullName())
                .cardCode(card.getCardCode())
                .createdAt(card.getCreatedAt())
                .updatedAt(card.getUpdatedAt())
                .users(this.userService.getEntity(card.getUserId()).getData())
                .build();
    }

    public Card updateCard(ResponseCardDto dto, Card card) {
        if (dto.getCardName() != null) {
            card.setCardName(dto.getCardName());
        }
        if (dto.getCardCode() != null) {
            card.setCardCode(dto.getCardCode());
        }
//        if (dto.getCardFullName() != null){
//            card.setCardFullName(dto.getCardFullName());
//        }
        card.setUpdatedAt(LocalDateTime.now());
        return card;
    }

    public Set<ResponseCardDto> fromEntityToDto(Set<Card> cardList){
        Set<ResponseCardDto> list = new HashSet<>();
        for (Card card : cardList) {
            list.add(this.toDto(card));
        }
        return list;
    }*/
}
