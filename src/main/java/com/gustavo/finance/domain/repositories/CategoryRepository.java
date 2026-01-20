package com.gustavo.finance.domain.repositories;

import com.gustavo.finance.domain.entities.Category;
import com.gustavo.finance.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameAndUser(String name, User user);

    List<Category> findByUser(User user);
}