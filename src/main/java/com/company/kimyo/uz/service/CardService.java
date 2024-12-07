package com.company.kimyo.uz.service;


import com.company.kimyo.uz.dto.request.RequestCardDto;
import com.company.kimyo.uz.dto.response.ResponseCardDto;
import com.company.kimyo.uz.dto.ResponseDto;
import com.company.kimyo.uz.entity.Card;
import com.company.kimyo.uz.repository.CardRepository;
import com.company.kimyo.uz.service.mapper.CardMapper;
import com.company.kimyo.uz.service.validation.CardValidation;
import com.company.kimyo.uz.util.SimpleCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
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
public class CardService implements SimpleCrud<Integer, ResponseCardDto, RequestCardDto> {


    @Lazy
    private final CardMapper cardMapper;
    private final CardValidation cardValidation;
    private final CardRepository cardRepository;

    @Override
    public ResponseDto<ResponseCardDto> createEntity(RequestCardDto dto) {

        /*List<ErrorDto> errorList = this.cardValidation.cardValid(dto);
        if (!errorList.isEmpty()) {
            return ResponseDto.<ResponseCardDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errorList(errorList).build();
        }*/

        try {
            Card entity = this.cardMapper.toEntity(dto);
            entity.setCreatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseCardDto>builder()
                    .success(true)
                    .message("OK")
                    .data(
                            this.cardMapper.toDto(
                                    this.cardRepository.save(
                                            entity
                                    )
                            )
                    ).build();

        } catch (Exception e) {
            return ResponseDto.<ResponseCardDto>builder()
                    .code(-2)
                    .message(String.format("Card error while saving; message :: %s", e.getMessage())).build();
        }
    }

    @Override
    public ResponseDto<ResponseCardDto> getEntity(Integer entityId) {
        Optional<Card> optional = this.cardRepository.findByCardIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseCardDto>builder()
                    .code(-1)
                    .message(String.format("Card with %d id is not found", entityId))
                    .build();
        }
        return ResponseDto.<ResponseCardDto>builder()
                .success(true)
                .message("OK")
                .data(this.cardMapper.toDto(optional.get())).build();

    }

    @Override
    public ResponseDto<ResponseCardDto> updateEntity(Integer entityid, RequestCardDto dto) {
        Optional<Card> optional = this.cardRepository.findByCardIdAndDeletedAtIsNull(entityid);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseCardDto>builder()
                    .code(-1)
                    .message(String.format("Card with %d id is not found", entityid))
                    .build();
        }
        return ResponseDto.<ResponseCardDto>builder()
                .success(true)
                .message("OK")
                .data(this.cardMapper.toDto(
                                this.cardRepository.save(
                                        this.cardMapper.updateCard(
                                                dto, optional.get()
                                        )
                                )
                        )
                )
                .build();

    }

    @Override
    public ResponseDto<ResponseCardDto> deleteEntity(Integer entityId) {
        Optional<Card> optional = this.cardRepository.findByCardId(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseCardDto>builder()
                    .code(-1)
                    .message(String.format("Card with %d id is not found", entityId))
                    .build();
        }
        Card card = optional.get();
        card.setDeletedAt(LocalDateTime.now());
        return ResponseDto.<ResponseCardDto>builder()
                .success(true)
                .message("OK")
                .data(
                        this.cardMapper.toDto(
                                this.cardRepository.save(card))
                ).build();

    }

    public ResponseDto<List<ResponseCardDto>> getAllCards() {
        List<Card> cards = this.cardRepository.findAll();
        if (cards.isEmpty()) {
            return ResponseDto.<List<ResponseCardDto>>builder()
                    .code(-1)
                    .message("Cards are not found")
                    .build();
        }
        return ResponseDto.<List<ResponseCardDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        cards.stream().map(this.cardMapper::toDto).toList()
                )
                .build();
    }

    public ResponseDto<Page<ResponseCardDto>> getAllCardByPage(Integer size, Integer page) {
        return this.getPageResponseDto(this.cardRepository.findAll(PageRequest.of(page, size)));
    }

    public ResponseDto<Page<ResponseCardDto>> getAllCardSortedByColumn(Integer size, Integer page, String column) {
        return getPageResponseDto(this.cardRepository.findAll(PageRequest.of(page, size, Sort.by(column).ascending())));
    }
    private ResponseDto<Page<ResponseCardDto>> getPageResponseDto(Page<Card> cardPage) {
        if (cardPage.isEmpty()) {
            return ResponseDto.<Page<ResponseCardDto>>builder()
                    .code(-1)
                    .message("Users are not found")
                    .build();
        }
        return ResponseDto.<Page<ResponseCardDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        cardPage.map(this.cardMapper::toDto)
                ).build();
    }

    public ResponseDto<Map<String, List<ResponseCardDto>>> getAllCardByCategory() {
        return ResponseDto.<Map<String, List<ResponseCardDto>>>builder()
                .success(true)
                .message("OK")
                .data(
                        this.cardRepository.findAll()
                                .stream()
                                .map(this.cardMapper::toDto)
                                .collect(Collectors.groupingBy(ResponseCardDto::getCardName))
                ).build();
    }

    public ResponseDto<Page<ResponseCardDto>> cardBasicSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("size")){
            size = Integer.parseInt(params.get("size"));
        }
        if (params.containsKey("page")){
            page = Integer.parseInt(params.get("page"));
        }
        return ResponseDto.<Page<ResponseCardDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        this.cardRepository.searchAllUserWithMoreParams(
                                params.get("id") == null ? null : Integer.parseInt(params.get("id")),
                                params.get("cardName"),
                                params.get("cardFullName"),
                                params.get("cardCode"),
                                PageRequest.of(page, size)
                        ).map(this.cardMapper::toDto)
                ).build();
    }









    /*public ResponseDto<Set<ResponseCardDto>> getAllByDeleteIsNull() {
        Set<Card> cardList = this.cardRepository.findAllByDeletedAtIsNull();
        if (cardList.isEmpty()){
            return ResponseDto.<Set<ResponseCardDto>>builder()
                    .code(-1)
                    .message("Cards are not found")
                    .build();
        }
        Set<ResponseCardDto> cardDtoList = this.cardMapper.fromEntityToDto(cardList);
        return ResponseDto.<Set<ResponseCardDto>>builder()
                .success(true)
                .message("OK")
//                .data(cardDtoList)
                .build();

    }*/
}
