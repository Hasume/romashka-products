package com.example.romashka_products.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Product {

    private Long id;

    @NotBlank(message = "Название товара обязательно")
    @Size(max = 255, message = "Название товара не должно превышать 255 символов")
    private String name;

    @Size(max = 4096, message = "Описание товара не должно превышать 4096 символов")
    private String description;

    @Min(value = 0, message = "Цена товара не может быть меньше 0")
    private double price = 0.0;

    private boolean inStock = false;

    // Конструкторы
    public Product() {
    }

    public Product(Long id, String name, String description, double price, boolean inStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}
