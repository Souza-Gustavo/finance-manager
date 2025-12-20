package com.gustavo.domain.services;


import com.gustavo.domain.entities.User;
import com.gustavo.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

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
    
}
