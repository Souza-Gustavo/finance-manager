package com.gustavo.finance.domain.services;


import org.springframework.stereotype.Service;

import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User cadastrar(User user) {

        boolean emailJaExiste = userRepository.findByEmail(user.getEmail()).isPresent();

        if (emailJaExiste) {
            throw new RuntimeException("Já existe um usuário cadastrado com esse e-mail.");
        }

        return userRepository.save(user);
    }

    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    
}
