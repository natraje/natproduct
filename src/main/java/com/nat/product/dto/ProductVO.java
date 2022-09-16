package com.nat.product.dto;

import com.nat.product.model.ProductDiscount;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;


public class ProductVO {

    private long id;
    @NotEmpty(message = "Please provide a product name")
    private String productName;
    private String description;
    @DecimalMin(value = ".01", message = "Please enter a valid product price")
    private double price;
    private String discount;

    public ProductVO(){}

    public ProductVO(@NotEmpty(message = "Please provide a product name") String productName, String description, @DecimalMin(value = ".01", message = "Please enter a valid product price") double price, String discount) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.discount = discount;
    }

    public ProductVO(long id, @NotEmpty(message = "Please provide a product name") String productName, String description, @DecimalMin(value = ".01", message = "Please enter a valid product price") double price, String discount) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
