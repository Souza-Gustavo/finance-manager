package com.gustavo.finance.application.controllers;

import com.gustavo.finance.application.dto.category.CategoryResponse;
import com.gustavo.finance.application.dto.category.CreateCategoryRequest;
import com.gustavo.finance.domain.entities.Category;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(
        @Valid @RequestBody CreateCategoryRequest request,
        Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        Category category = categoryService.create(request.getName(), user);

        CategoryResponse response =
            new CategoryResponse(category.getId(), category.getName());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> list(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        List<CategoryResponse> response = categoryService.listByUser(user)
            .stream()
            .map(category ->
                new CategoryResponse(category.getId(), category.getName())
            )
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
