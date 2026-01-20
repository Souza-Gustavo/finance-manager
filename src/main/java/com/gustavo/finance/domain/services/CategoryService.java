package com.gustavo.finance.domain.services;

import com.gustavo.finance.application.dto.category.CreateCategoryRequestDTO;
import com.gustavo.finance.domain.entities.Category;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(CreateCategoryRequestDTO request, User user) {

        categoryRepository.findByNameAndUser(request.getName(), user)
                .ifPresent(c -> {
                    throw new IllegalArgumentException("Categoria já existe");
                });

        Category category = new Category();
        category.setName(request.getName());
        category.setUser(user);

        return categoryRepository.save(category);
    }

    public List<Category> listByUser(User user) {
        return categoryRepository.findByUser(user);
    }
}

