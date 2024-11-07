package com.example.romashka_products.service;

import com.example.romashka_products.model.Product;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {
    private final Map<Long, Product> productRepo = new HashMap<>();
    private Long nextId = 1L;

    public List<Product> getAllProducts() {
        return new ArrayList<>(productRepo.values());
    }

    public Product getProductById(Long id) {
        return productRepo.get(id);
    }

    public Product createProduct(Product product) {
        product.setId(nextId++);
        productRepo.put(product.getId(), product);
        return product;
    }

    public Product updateProduct(Long id, Product product) {
        if (productRepo.containsKey(id)) {
            product.setId(id);
            productRepo.put(id, product);
            return product;
        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        return productRepo.remove(id) != null;
    }
}