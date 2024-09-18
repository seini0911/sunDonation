package com.sunshine.donations.controller;

import com.sunshine.donations.requests.RegisterUserRequest;
import com.sunshine.donations.response.ApiResponseHandler;
import com.sunshine.donations.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
    /* User service Injection */
    private final IUserService userService;

    /* Endpoint to register a new user account */
    @PostMapping
    public ResponseEntity<Object> register(@RequestBody RegisterUserRequest userRequest) {
        try {
            return ApiResponseHandler.buildResponse(
                    "User registered successfully",
                    HttpStatus.CREATED,
                    userService.registerNewUser(userRequest)
            );
        } catch (Exception e) {
            return ApiResponseHandler.buildResponse(
                    "An error occurred when registering user",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }

    /* Endpoint to get all users accounts */
    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        try {
            return ApiResponseHandler.buildResponse(
                    "Users list",
                    HttpStatus.OK,
                    userService.getAllUsers()
            );
        } catch (Exception e) {
            return ApiResponseHandler.buildResponse(
                    "An error occurred while getting users list",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }

    /* Endpoint to get a user account by his userId */
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable Long userId){
        try{
            return  ApiResponseHandler.buildResponse(
                "User retrieved",
                HttpStatus.OK,
                userService.findUserById(userId)
            );
        }catch(Exception e){
            return ApiResponseHandler.buildResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                null
            );
        }
    }
}
