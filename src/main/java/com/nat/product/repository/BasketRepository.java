package com.nat.product.repository;

import com.nat.product.model.Basket;
import com.nat.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}
