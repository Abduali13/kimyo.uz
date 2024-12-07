package com.company.kimyo.uz.controller;

import com.company.kimyo.uz.dto.request.RequestCategoryDto;
import com.company.kimyo.uz.dto.response.ResponseCategoryDto;
import com.company.kimyo.uz.dto.ResponseDto;
import com.company.kimyo.uz.service.CategoryService;
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

@RestController
@RequestMapping(value = "category")
@RequiredArgsConstructor
public class CategoryController implements SimpleRequestCrud<Integer, ResponseCategoryDto, RequestCategoryDto> {

    private final CategoryService categoryService;

    @Override
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Category API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Bad Request Post Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Not Found Post Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Internal Server Error Post Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseCategoryDto>> createEntity(@RequestBody @Valid RequestCategoryDto entity) {
        return convertStatusByData(this.categoryService.createEntity(entity));
    }

    @Override
    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Category API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseCategoryDto>> getEntity(@RequestParam(value = "id") Integer entityId) {
        return convertStatusByData(this.categoryService.getEntity(entityId));
    }

    @Override
    @PutMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Category API Success Put Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Bad Request Put Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Not Found Put Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Internal Server Error Put Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseCategoryDto>> updateEntity(@RequestParam(value = "id") Integer entityId, @RequestBody RequestCategoryDto entity) {
        return convertStatusByData(this.categoryService.updateEntity(entityId, entity));
    }

    @Override
    @DeleteMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Category API Success Delete Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Bad Request Delete Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Not Found Delete Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Internal Server Error Delete Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseCategoryDto>> deleteEntity(@RequestParam(value = "id") Integer entityId) {
        return convertStatusByData(this.categoryService.deleteEntity(entityId));
    }


    @GetMapping(value = "/get-all")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Category API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<List<ResponseCategoryDto>> getAllCategory(){
        return this.categoryService.getAllCategory();
    }

    @GetMapping(value = "/get-all-by-page")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Category API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Page<ResponseCategoryDto>> getAllCategoryByPage(
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "page") Integer page
    ){
        return this.categoryService.getAllCategoryByPage(size, page);
    }

    @GetMapping(value = "/get-all-by-sort")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Category API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Page<ResponseCategoryDto>> getAllCategorySortedByColumn(
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "column") String column
    ){
        return this.categoryService.getAllCategorySortedByColumn(size, page, column);
    }

    @GetMapping(value = "/get-all-by-category")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Category API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Map<String, List<ResponseCategoryDto>>> getAllCategoryByCategory(){
        return this.categoryService.getAllCategoryByCategory();
    }


    @GetMapping(value = "/category-search")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Category API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Category API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Page<ResponseCategoryDto>> categoryBasicSearch(
            @RequestParam Map<String, String> params
    ){
        return this.categoryService.categoryBasicSearch(params);
    }


}
