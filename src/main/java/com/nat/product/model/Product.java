package com.nat.product.model;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="productName")
    private String productName;
    @Column(name="description")
    private String description;
    @Column(name="price")
    private double price;
    @Column(name="discount")
    private String discount;
    //private Enum<ProductDiscount> discount;

    public Product(){}
    public Product(long id, String productName, String description, double price, String discount) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.discount = discount;
    }

    public Product(String productName, String description, double price, String discount) {
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("id=").append(id);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", discount=").append(discount);
        sb.append('}');
        return sb.toString();
    }

}
