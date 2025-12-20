package com.gustavo.application.controllers;



import com.gustavo.domain.services.UserService;
import com.gustavo.domain.entities.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> cadastrar(@RequestBody User user) {
        User usuarioSalvo = userService.cadastrar(user);
         return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }
    
}
