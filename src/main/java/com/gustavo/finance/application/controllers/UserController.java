package com.gustavo.finance.application.controllers;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gustavo.finance.application.dto.UserCreateDTO;
import com.gustavo.finance.application.dto.UserResponseDTO;
import com.gustavo.finance.domain.services.UserService;
import com.gustavo.finance.application.dto.LoginRequestDTO;

import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
        public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO dto) {

         UserResponseDTO response = userService.login(dto);

    return ResponseEntity.ok(response);
}

   @PostMapping
    public ResponseEntity<UserResponseDTO> cadastrar(
       @Valid @RequestBody UserCreateDTO dto) {

     return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.cadastrar(dto));
}

    @GetMapping
        public ResponseEntity<List<UserResponseDTO>> listar() {
        return ResponseEntity.ok(userService.listarTodos());
    }
    
}
