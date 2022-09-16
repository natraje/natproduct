package com.nat.product.dto;

public class ItemsVO {

    private long productId;
    private String discount;
    private int quantity;
    private String productName;
    private String price;

    public ItemsVO(){}

    public ItemsVO(long productId, String discount, int quantity, String productName) {
        this.productId = productId;
        this.discount = discount;
        this.quantity = quantity;
        this.productName = productName;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
