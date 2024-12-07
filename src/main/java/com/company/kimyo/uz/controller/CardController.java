package com.company.kimyo.uz.controller;

import com.company.kimyo.uz.dto.request.RequestCardDto;
import com.company.kimyo.uz.dto.response.ResponseCardDto;
import com.company.kimyo.uz.dto.ResponseDto;
import com.company.kimyo.uz.service.CardService;
import com.company.kimyo.uz.util.SimpleRequestCrud;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.company.kimyo.uz.config.SimpleResponseDto.convertStatusByData;
import static com.company.kimyo.uz.constants.SwaggerConstants.*;


// todo: DI -> dependecy injection

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "cards")
public class CardController implements SimpleRequestCrud<Integer, ResponseCardDto, RequestCardDto> {

    private final CardService cardService;

    @Override
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Cards API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARD_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Bad Request Post Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Not Found Post Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Internal Server Error Post Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseCardDto>> createEntity(@RequestBody @Valid RequestCardDto entity) {
        return convertStatusByData(this.cardService.createEntity(entity));
    }

    @Override
    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Cards API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARD_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseCardDto>> getEntity(@RequestParam(value = "id") Integer entityId) {
        return convertStatusByData(this.cardService.getEntity(entityId));
    }

    @Override
    @PutMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Cards API Success Put Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARD_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Bad Request Put Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Not Found Put Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Internal Server Error Put Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseCardDto>> updateEntity(@RequestParam(value = "id")Integer entityid, @RequestBody RequestCardDto entity) {
        return convertStatusByData(this.cardService.updateEntity(entityid, entity));
    }

    @Override
    @DeleteMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Cards API Success Delete Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARD_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Bad Request Delete Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Not Found Delete Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Internal Server Error Delete Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseCardDto>> deleteEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusByData(this.cardService.deleteEntity(entityId));
    }

    @GetMapping({"get-all", "get-all-by-cards"})
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Cards API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARD_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<List<ResponseCardDto>> getAllCards(){
        return this.cardService.getAllCards();
    }
    @GetMapping(value = "/get-all-by-page")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Cards API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARD_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Page<ResponseCardDto>> getAllCardByPage(
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "page") Integer page
    ){
        return this.cardService.getAllCardByPage(size, page);
    }

    @GetMapping(value = "/get-all-by-sort")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Cards API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARD_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Page<ResponseCardDto>> getAllCardSortedByColumn(
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "column") String column
    ){
        return this.cardService.getAllCardSortedByColumn(size, page, column);
    }

    @GetMapping(value = "/get-all-by-category")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Cards API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARD_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Map<String, List<ResponseCardDto>>> getAllCardByCategory(){
        return this.cardService.getAllCardByCategory();
    }

    @GetMapping(value = "/card-search")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Cards API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARD_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Cards API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CARDS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Page<ResponseCardDto>> cardBasicSearch(
            @RequestParam Map<String, String> params
    ){
        return this.cardService.cardBasicSearch(params);
    }









    /*@Lazy
    @Autowired
    private UserController userController;
    private List<Card> cards;
    private Integer cardIx;

    public CardController() {
        this.cards = new ArrayList<>();
        this.cardIx = 0;
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> createCard(@RequestBody Card card) {
        if (this.userController.get(card.getUserId()).getBody() == null) {
            return ResponseEntity.ok(String.format("User with %d is not found", card.getUserId()));
        }
        card.setCardId(++this.cardIx);
        this.cards.add(card);
        return ResponseEntity.ok().body("Card successfully created");
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCard(@PathVariable(value = "id") Integer cardid) {
        for (Card card : this.cards) {
            if (card.getCardId().equals(cardid)) {
                return ResponseEntity.ok(card);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCard(@PathVariable(value = "id") Integer cardid,
                                        @RequestBody Card newCard) {
        for (Card oldCard : this.cards) {
            if (oldCard.getCardId().equals(cardid)) {
                oldCard.setCardId(cardid);
                this.cards.set(this.cards.indexOf(oldCard), updateCardToEntity(newCard, oldCard));
                return ResponseEntity.ok().body("Card successfully updated");
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable(value = "id") Integer cardid) {
        for (Card card : this.cards) {
            if (card.getCardId().equals(cardid)) {
                this.cards.remove(card);
                return ResponseEntity.ok().body("Card successfully deleted");
            }
        }
        return ResponseEntity.notFound().build();
    }

    private Card updateCardToEntity(Card newCard, Card oldCard) {
        if (newCard.getCardName() != null) {
            oldCard.setCardName(newCard.getCardName());
        }
        if (newCard.getCardCode() != null) {
            oldCard.setCardName(newCard.getCardName());
        }
        return oldCard;
    }

    public Set<Card> getAllCardsByUserId(Integer userId) {
        Set<Card> c = new HashSet<>();
        for (Card card : this.cards) {
            if (card.getUserId().equals(userId)) {
                c.add(card);
            }
        }
        return c;
    }*/
}
