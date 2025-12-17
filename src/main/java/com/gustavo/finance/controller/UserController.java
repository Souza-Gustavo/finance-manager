package com.gustavo.finance.controller;

import com.gustavo.finance.entity.User;
import com.gustavo.finance.service.UserService;
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
