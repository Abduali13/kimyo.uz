package com.company.kimyo.uz.controller;

import com.company.kimyo.uz.dto.ResponseDto;
import com.company.kimyo.uz.dto.request.RequestUserDto;
import com.company.kimyo.uz.dto.response.ResponseUserDto;
import com.company.kimyo.uz.service.UserService;
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


// todo: localhost:8080/users

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "users")
public class UserController implements SimpleRequestCrud<Integer, ResponseUserDto, RequestUserDto> {
    // todo: Http -> Hyper text transaction protocol
    // todo: Https -> Hyper text transaction protocol secure
    // todo: API -> Application Properties Interface

    private final UserService userService;

    @Override
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Users API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Bad Request Post Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Not Found Post Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Internal Server Error Post Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseUserDto>> createEntity(@RequestBody @Valid RequestUserDto entity) {
        return convertStatusByData(this.userService.createEntity(entity));
    }

    @Override
    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Users API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseUserDto>> getEntity(@RequestParam(value = "id") Integer entityId) {
        return convertStatusByData(this.userService.getEntity(entityId));
    }

    @Override
    @PutMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Users API Success Put Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Bad Request Put Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Not Found Put Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Internal Server Error Put Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseUserDto>>updateEntity(@RequestParam(value = "id") Integer entityid, @RequestBody RequestUserDto entity) {
        return convertStatusByData(this.userService.updateEntity(entityid, entity));
    }

    @Override
    @DeleteMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Users API Success Delete Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Bad Request Delete Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Not Found Delete Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Internal Server Error Delete Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseDto<ResponseUserDto>> deleteEntity(@RequestParam(value = "id")  Integer entityId) {
        return convertStatusByData(this.userService.deleteEntity(entityId));
    }

    @GetMapping(value = "/get-all")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Users API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<List<ResponseUserDto>> getAllUser(){
        return this.userService.getAllUser();
    }

    @GetMapping(value = "/get-all-by-page")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Users API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Page<ResponseUserDto>> getAllUserByPage(
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "page") Integer page
    ){
        return this.userService.getAllUserByPage(size, page);
    }

    @GetMapping(value = "/get-all-by-sort")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Users API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Page<ResponseUserDto>> getAllUserSortedByColumn(
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "cl") String column
    ){
        return this.userService.getAllUserSortedByColumn(size, page, column);
    }

    @GetMapping(value = "/get-all-by-category")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Users API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Map<String, List<ResponseUserDto>>> getAllUsersByCategory(){
        return this.userService.getAllUsersByCategory();
    }

    @GetMapping(value = "/user-search")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "Users API Success Get Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_SUCCESS)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Bad Request Get Method",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_BAD_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Not Found Get Method",
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_NOT_FOUND)
                            )
                    ),
                    @ApiResponse(
                            description = "Users API Internal Server Error Get Method",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = @ExampleObject(value = EXAMPLE_USERS_INTERNAL_SERVER_ERROR)
                            )
                    )
            }
    )
    public ResponseDto<Page<ResponseUserDto>> userBasicSearch(
            @RequestParam Map<String, String> params)
    {
        return this.userService.userBasicSearch(params);
    }



    /*private CardController cardController;

    public UserController(CardController cardController) {
        this.cardController = cardController;
        this.users = new ArrayList<>();
        this.userIx = 0;
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody User user) {
        user.setUserId(++this.userIx);
        this.users.add(user);
        return ResponseEntity.ok().body("User successfully created");
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam(value = "id") Integer userId) {
        for (User user : this.users) {
            if (user.getUserId().equals(userId)) {
                user.setCard(cardController.getAllCardsByUserId(userId));
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer userId, @RequestBody User newUser) {
        for (User oldUser : this.users) {
            if (oldUser.getUserId().equals(userId)) {
                newUser.setUserId(userId);
                this.users.set(users.indexOf(oldUser), updateUserToEntity(newUser, oldUser));
                return ResponseEntity.ok().body("User successfully updated");
            }
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer userId) {
        for (User user : this.users) {
            if (user.getUserId().equals(userId)) {
                this.users.remove(user);
                return ResponseEntity.ok().body("User successfully deleted");
            }
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping(value = "/get-all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.users);
    }


    private User updateUserToEntity(User newUser, User oldUser) {
        if (newUser.getFirstname() != null) {
            oldUser.setFirstname(newUser.getFirstname());
        }
        if (newUser.getLastname() != null) {
            oldUser.setLastname(newUser.getLastname());
        }
        if (newUser.getEmail() != null) {
            oldUser.setEmail(newUser.getEmail());
        }
        if (newUser.getAge() != null) {
            oldUser.setAge(newUser.getAge());
        }

        return oldUser;
    }

    @GetMapping(value = "/get/{data}")
    public String getValue(@RequestParam(value = "id") Integer id,
                           @RequestParam(value = "color") String color,
                           @PathVariable String data) {
        return String.format("Value one: %d, value two: %s, value three: %s", id, color, data);
    }*/
}
