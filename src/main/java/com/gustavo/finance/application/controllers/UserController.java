package com.gustavo.finance.application.controllers;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.services.UserService;


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
