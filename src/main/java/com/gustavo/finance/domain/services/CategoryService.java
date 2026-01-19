package com.gustavo.finance.domain.services;

import com.gustavo.finance.domain.entities.Category;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(String name, User user) {

        CategoryRepository.findByNameAndUser(name, user)
            .ifPresent(category -> {
                throw new IllegalArgumentException("Categoria já existe");
            });

        Category category = new Category(name, user);
        return categoryRepository.save(category);
    }

    public List<Category> listByUser(User user) {
        return categoryRepository.findAllByUser(user);
    }

}