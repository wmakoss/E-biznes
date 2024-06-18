package org.example.zadanie8.controller;

import org.example.zadanie8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String users() {
        return String.valueOf(userService.getUsers().size());
    }
}
