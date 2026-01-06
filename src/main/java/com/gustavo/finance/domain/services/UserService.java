package com.gustavo.finance.domain.services;


import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gustavo.finance.application.dto.LoginRequestDTO;
import com.gustavo.finance.application.dto.UserCreateDTO;
import com.gustavo.finance.application.dto.UserResponseDTO;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.repositories.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO login(LoginRequestDTO dto) {

    User user = userRepository.findByEmail(dto.getEmail())
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.UNAUTHORIZED, "Email ou senha inválidos"
        ));

    if (!passwordEncoder.matches(dto.getSenha(), user.getSenha())) {
        throw new ResponseStatusException(
            HttpStatus.UNAUTHORIZED,
             "Email ou senha inválidos"
    );
}

    return new UserResponseDTO(
        user.getId(),
        user.getName(),
        user.getEmail()
    );
}

    public UserResponseDTO cadastrar(UserCreateDTO dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setSenha(passwordEncoder.encode(dto.getSenha()));

        User salvo = userRepository.save(user);

        return new UserResponseDTO(
                salvo.getId(),
                salvo.getName(),
                salvo.getEmail()
        );
    }

    public List<UserResponseDTO> listarTodos() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .toList();
    }

    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
