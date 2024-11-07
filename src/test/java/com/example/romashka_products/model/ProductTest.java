package com.example.romashka_products.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
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
        Product product = new Product(1L, "Valid Name", "Valid description", 10.0, true);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(0, violations.size());
    }

    @Test
    void testInvalidName() {
        Product product = new Product(1L, "", "Description", 10.0, true);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(1, violations.size());
    }

    @Test
    void testInvalidPrice() {
        Product product = new Product(1L, "Product", "Description", -5.0, true);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(1, violations.size());
    }
}