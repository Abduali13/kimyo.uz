package com.company.kimyo.uz.controller;

import com.company.kimyo.uz.dto.FileDto;
import com.company.kimyo.uz.dto.ResponseDto;
import com.company.kimyo.uz.service.FileService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileDescriptor;

import static com.company.kimyo.uz.constants.SwaggerConstants.*;
import static com.company.kimyo.uz.constants.SwaggerConstants.EXAMPLE_ORDERS_INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping(value = "file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping(
            value = "/upload-file",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    }
            )
    })
    public ResponseDto<FileDto> uploadFile(@RequestBody MultipartFile file){
        return this.fileService.uploadFile(file);
    }

    @GetMapping(value = "/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "File API Success GET Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "File API Bad Request GET Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "File API Not Found GET Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "File API Internal Server Error GET Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<FileDto> downloadFile(@PathVariable(value = "id") Integer fileId){
        return this.fileService.downloadFile(fileId);
    }

    @DeleteMapping(value = "/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "File API Success DELETE Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "File API Bad Request DELETE Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "File API Not Found DELETE Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "File API Internal Server Error DELETE Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<FileDto> deleteFile(@PathVariable(value = "id") Integer fileId){
        return this.fileService.deleteFile(fileId);
    }

}
