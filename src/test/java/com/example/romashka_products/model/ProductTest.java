package com.example.romashka_products.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidProduct() {
        Product product = new Product("Valid Name", "Valid description", 10.0, true);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(0, violations.size(), "Expected no validation errors for valid product");
    }

    @Test
    void testInvalidName() {
        Product product = new Product("", "Description", 10.0, true);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(1, violations.size(), "Expected 1 validation error for empty name");

        ConstraintViolation<Product> violation = violations.iterator().next();
        assertEquals("Название товара обязательно", violation.getMessage());
    }

    @Test
    void testNameExceedsMaxLength() {
        String longName = "a".repeat(256); // Создаем строку длиной 256 символов
        Product product = new Product(longName, "Description", 10.0, true);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(1, violations.size(), "Expected 1 validation error for name exceeding 255 characters");

        ConstraintViolation<Product> violation = violations.iterator().next();
        assertEquals("Название товара не должно превышать 255 символов", violation.getMessage());
    }

    @Test
    void testInvalidPrice() {
        Product product = new Product("Product", "Description", -5.0, true);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(1, violations.size(), "Expected 1 validation error for negative price");

        ConstraintViolation<Product> violation = violations.iterator().next();
        assertEquals("Цена товара не может быть меньше 0", violation.getMessage());
    }

    @Test
    void testDescriptionExceedsMaxLength() {
        String longDescription = "a".repeat(4097); // Создаем строку длиной 4097 символов
        Product product = new Product("Product", longDescription, 10.0, true);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(1, violations.size(), "Expected 1 validation error for description exceeding 4096 characters");

        ConstraintViolation<Product> violation = violations.iterator().next();
        assertEquals("Описание товара не должно превышать 4096 символов", violation.getMessage());
    }
}
