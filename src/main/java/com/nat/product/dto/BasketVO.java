package com.nat.product.dto;

import com.nat.product.model.Cart;

import java.util.List;

public class BasketVO {

    private long basketId;
    private List<ItemsVO> items;
    private double total;

    public BasketVO(){}

    public BasketVO(long basketId, List<ItemsVO> items) {
        this.basketId = basketId;
        this.items = items;
    }

    public long getBasketId() {
        return basketId;
    }

    public void setBasketId(long basketId) {
        this.basketId = basketId;
    }

    public List<ItemsVO> getItems() {
        return items;
    }

    public void setItems(List<ItemsVO> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
