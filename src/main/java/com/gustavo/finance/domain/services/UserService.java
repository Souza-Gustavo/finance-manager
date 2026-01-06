package com.gustavo.finance.domain.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gustavo.finance.application.dto.LoginRequestDTO;
import com.gustavo.finance.application.dto.UserCreateDTO;
import com.gustavo.finance.application.dto.UserResponseDTO;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.repositories.UserRepository;
import com.gustavo.finance.infrastructure.security.JwtService;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    
    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

   
    public String login(LoginRequestDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Email ou senha inválidos"
                ));

        if (!passwordEncoder.matches(dto.getSenha(), user.getSenha())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Email ou senha inválidos"
            );
        }

        // 🔐 GERAR TOKEN
        return jwtService.gerarToken(user.getEmail());
    }

    public UserResponseDTO me(String authorization) {

    if (!authorization.startsWith("Bearer ")) {
        throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Token inválido"
        );
    }

    String token = authorization.replace("Bearer ", "");

    String email = jwtService.getSubject(token);

    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Usuário não encontrado"
            ));

    return new UserResponseDTO(
            user.getId(),
            user.getName(),
            user.getEmail()
    );
}

    // ✅ CADASTRO
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

    // ✅ LISTAGEM
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
