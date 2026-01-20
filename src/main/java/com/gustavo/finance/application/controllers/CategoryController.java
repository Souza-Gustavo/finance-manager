package com.gustavo.finance.application.controllers;

import com.gustavo.finance.application.dto.category.CategoryResponseDTO;
import com.gustavo.finance.application.dto.category.CreateCategoryRequestDTO;
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
    public ResponseEntity<CategoryResponseDTO> create(
        @Valid @RequestBody CreateCategoryRequestDTO request,
        Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        Category category = categoryService.create(request, user);

        CategoryResponseDTO response =
            new CategoryResponseDTO(category.getId(), category.getName());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> list(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        List<CategoryResponseDTO> response = categoryService.listByUser(user)
            .stream()
            .map(category ->
                new CategoryResponseDTO(category.getId(), category.getName())
            )
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
