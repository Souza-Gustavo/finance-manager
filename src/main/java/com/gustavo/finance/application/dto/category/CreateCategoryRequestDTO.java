package com.gustavo.finance.application.dto.category;

import jakarta.validation.constraints.NotBlank;

public class CreateCategoryRequestDTO {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}