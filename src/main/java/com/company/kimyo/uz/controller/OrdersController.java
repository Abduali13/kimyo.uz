package com.company.kimyo.uz.controller;


import com.company.kimyo.uz.dto.request.RequestOrdersDto;
import com.company.kimyo.uz.dto.response.ResponseOrdersDto;
import com.company.kimyo.uz.dto.ResponseDto;
import com.company.kimyo.uz.service.OrdersService;
import com.company.kimyo.uz.util.SimpleRequestCrud;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.company.kimyo.uz.config.SimpleResponseDto.convertStatusByData;
import static com.company.kimyo.uz.constants.SwaggerConstants.*;



@RestController
@RequestMapping(value = "orders")
@RequiredArgsConstructor
public class OrdersController implements SimpleRequestCrud<Integer, ResponseOrdersDto, RequestOrdersDto> {

    private final OrdersService ordersService;

    @Override
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Orders API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Bad Request Post Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Not Found Post Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Internal Server Error Post Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseOrdersDto>> createEntity(@RequestBody RequestOrdersDto dto) {
        return convertStatusByData(this.ordersService.createEntity(dto));
    }

    @Override
    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Orders API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseOrdersDto>> getEntity(@RequestParam(value = "id") Integer entityId) {
        return convertStatusByData(this.ordersService.getEntity(entityId));
    }

    @Override
    @PutMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Orders API Success Put Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Bad Request Put Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Not Found Put Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Internal Server Error Put Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseOrdersDto>> updateEntity(@RequestParam(value = "id") Integer entityId, @RequestBody RequestOrdersDto dto) {
        return convertStatusByData(this.ordersService.updateEntity(entityId, dto));
    }

    @Override
    @DeleteMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Orders API Success Delete Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Bad Request Delete Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Not Found Delete Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Internal Server Error Delete Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseOrdersDto>> deleteEntity(@RequestParam(value = "id") Integer entityId) {
        return convertStatusByData(this.ordersService.deleteEntity(entityId));
    }



    @GetMapping(value = "/get-all")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Orders API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<List<ResponseOrdersDto>> getAllOrders(){
        return this.ordersService.getAllOrders();
    }

    @GetMapping(value = "/get-all-by-page")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Orders API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Page<ResponseOrdersDto>> getAllOrdersByPage(
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "page") Integer page
    ){
        return this.ordersService.getAllOrdersByPage(size, page);
    }


    @GetMapping(value = "/get-all-by-sort")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Orders API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Page<ResponseOrdersDto>> getAllOrdersSortedByColumn(
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "column") String column
    ){
        return this.ordersService.getAllOrdersSortedByColumn(size, page, column);
    }


    @GetMapping(value = "/get-all-by-category")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Orders API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Map<LocalDate, List<ResponseOrdersDto>>> getAllOrdersByCategory(){
        return this.ordersService.getAllOrdersByCategory();
    }


    @GetMapping(value = "/orders-search")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Orders API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Orders API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Page<ResponseOrdersDto>> ordersBasicSearch(
            @RequestParam Map<String, String> params){
        return this.ordersService.ordersBasicSearch(params);
    }


}

