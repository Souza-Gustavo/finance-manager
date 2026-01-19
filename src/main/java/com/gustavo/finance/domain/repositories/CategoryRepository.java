package com.gustavo.finance.domain.repositories;

import com.seuprojeto.domain.entity.Category;
import com.seuprojeto.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameAndUser(String name, User user);

    List<Category> findAllByUser(User user);
}