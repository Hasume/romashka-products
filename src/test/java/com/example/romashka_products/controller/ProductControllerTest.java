package com.example.romashka_products.controller;

import com.example.romashka_products.model.Product;
import com.example.romashka_products.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        productRepository.deleteAll();
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product product = new Product("Test Product", "Description", 100.0, true);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test Product")));
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Product product1 = new Product("Product1", "Description1", 50.0, true);
        Product product2 = new Product("Product2", "Description2", 60.0, false);
        productRepository.save(product1);
        productRepository.save(product2);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)));
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product("Product1", "Description", 50.0, true);
        product = productRepository.save(product);

        mockMvc.perform(get("/api/products/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Product1")));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product("Old Name", "Description", 50.0, true);
        product = productRepository.save(product);

        Product updatedProduct = new Product("Updated Name", "Updated Description", 70.0, false);

        mockMvc.perform(put("/api/products/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Name")));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product product = new Product("Test Product", "Description", 100.0, true);
        product = productRepository.save(product);

        mockMvc.perform(delete("/api/products/" + product.getId()))
                .andExpect(status().isNoContent());
    }
}
