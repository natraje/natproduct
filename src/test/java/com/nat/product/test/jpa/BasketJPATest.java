package com.nat.product.test.jpa;

import com.nat.product.model.Basket;
import com.nat.product.model.Product;
import com.nat.product.repository.BasketRepository;
import com.nat.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static com.nat.product.model.DiscountManager.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BasketJPATest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    ProductRepository repository;
    @Autowired
    BasketRepository basketRepository;

    @Test
    public void should_find_Nobasket() {
        Optional<Basket> b  = basketRepository.findById(1L);
        assertThat(b).isEmpty();
    }

//    @Test
//    public void should_addproducttobasket() {
//        Product p1 = new Product("Product 1", "Product 1 desc",10.01, TWNETYFIVEPERCENT);
//        entityManager.persist(p1);
//        Product p2 = new Product("Product 2", "Product 2 desc",12.01, NONE);
//        entityManager.persist(p2);
//
//        assertThat(products).hasSize(3).contains(p1, p2, p3);
//    }
}
