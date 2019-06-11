package com.epam.controller;

import com.epam.entity.response.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public ResponseEntity findAllUser() {
        return new ResponseEntity("", "");
    }
}
