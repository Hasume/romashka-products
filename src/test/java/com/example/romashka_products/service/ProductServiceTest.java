package com.example.romashka_products.service;

import com.example.romashka_products.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
    }

    @Test
    void testCreateProduct() {
        Product product = new Product(null, "Product Name", "Description", 10.0, true);
        Product created = productService.createProduct(product);
        assertNotNull(created.getId());
    }

    @Test
    void testGetProductById() {
        Product product = productService.getProductById(1L);
        assertNotNull(product);
        assertEquals("Product Name", product.getName());
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertFalse(products.isEmpty());
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct(1L);
        Optional<Product> product = Optional.ofNullable(productService.getProductById(1L));
        assertTrue(product.isEmpty());
    }
}