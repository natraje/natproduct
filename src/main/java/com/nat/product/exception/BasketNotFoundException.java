package com.nat.product.exception;

public class BasketNotFoundException extends RuntimeException {
    public BasketNotFoundException(Long id) {
        super("Could not find basket " + id);
    }

}
