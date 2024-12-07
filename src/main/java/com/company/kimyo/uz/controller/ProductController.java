package com.company.kimyo.uz.controller;

import com.company.kimyo.uz.dto.request.RequestProductDto;
import com.company.kimyo.uz.dto.response.ResponseProductDto;
import com.company.kimyo.uz.dto.ResponseDto;
import com.company.kimyo.uz.service.ProductService;
import com.company.kimyo.uz.util.SimpleCrud;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.company.kimyo.uz.constants.SwaggerConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "product")
@Tag(name = "Products", description = "Products API")
public class ProductController implements SimpleCrud<Integer, ResponseProductDto, RequestProductDto> {

    private final ProductService productService;

    @Override
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Products API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Post Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Post Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Post Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    @Operation(summary = "This is product Post method")
    public ResponseDto<ResponseProductDto> createEntity(@RequestBody RequestProductDto product) {
        return this.productService.createEntity(product);
    }

    @Override
    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Products API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<ResponseProductDto> getEntity(@RequestParam(value = "id") Integer prodId) {
        return this.productService.getEntity(prodId);
    }

    @Override
    @PutMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Products API Success Put Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Bad Request Put Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Put Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Put Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<ResponseProductDto> updateEntity(@RequestParam(value = "id") Integer prodId,
                                                        @RequestBody RequestProductDto product) {
        return this.productService.updateEntity(prodId, product);
    }

    @Override
    @DeleteMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Products API Success Delete Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Bad Request Delete Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Delete Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Delete Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<ResponseProductDto> deleteEntity(@RequestParam(value = "id") Integer prodId) {
        return this.productService.deleteEntity(prodId);
    }

    @GetMapping(value = "/get-all")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Products API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<List<ResponseProductDto>> getAll() {
        return this.productService.getAllProduct();
    }

    @GetMapping(value = "/get-all-by-page")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Products API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Page<ResponseProductDto>> getAllProductByPage(
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "page") Integer page
    ) {
        return this.productService.getAllProductByPage(size, page);
    }

    @GetMapping(value = "/get-all-by-sort")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Products API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Page<ResponseProductDto>> getAllProductSortByName(
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "cl") String column
    ) {
//        return this.productService.getAllProductSortByColumn(size, page, column);
        return null;
    }

    @GetMapping(value = "/get-all-by-category")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Products API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Map<String, List<ResponseProductDto>>> getAllProdByCategory() {
        return this.productService.getAllProdByCategory();
    }

    @GetMapping(value = "/prod-basic-search")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Products API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    /*schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),*/
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Products API Not Found Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Page<ResponseProductDto>> productBasicSearch(
            @RequestParam Map<String, String> params
    ) {
        return this.productService.productBasicSearch(params);
    }

    @GetMapping(value = "/prod-advanced-search")
    public ResponseDto<Page<ResponseProductDto>> productAdvancedSearch(
            @RequestParam Map<String, String> params
    ) {
        return this.productService.productAdvancedSearch(params);
    }


}
