package com.example.romashka_products.repository;

import com.example.romashka_products.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        productRepository.deleteAll();
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product("Test Product", "Description", 100.0, true);
        Product savedProduct = productRepository.save(product);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isNotNull();
    }

    @Test
    public void testFindById() {
        Product product = new Product("Test Product", "Description", 100.0, true);
        productRepository.save(product);

        Optional<Product> foundProduct = productRepository.findById(product.getId());
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isEqualTo("Test Product");
    }

    @Test
    public void testFindAll() {
        Product product1 = new Product("Product1", "Description1", 50.0, true);
        Product product2 = new Product("Product2", "Description2", 60.0, false);
        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2);
    }

    @Test
    public void testDeleteById() {
        Product product = new Product("Test Product", "Description", 100.0, true);
        product = productRepository.save(product);

        productRepository.deleteById(product.getId());
        Optional<Product> deletedProduct = productRepository.findById(product.getId());
        assertThat(deletedProduct).isNotPresent();
    }
}
