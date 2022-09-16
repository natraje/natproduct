package com.nat.product.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="basket")
public class Basket {

    @Id
    private long basketId;
    @Column(name="items")
    private String products;

    public Basket() {
    }

    public long getBasketId() {
        return basketId;
    }

    public void setBasketId(long basketId) {
        this.basketId = basketId;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }


}
