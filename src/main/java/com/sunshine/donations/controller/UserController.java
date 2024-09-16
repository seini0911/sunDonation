package com.sunshine.donations.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {


    @GetMapping
    public String getAllUsers(){
        return "Get all users";
    }
}
