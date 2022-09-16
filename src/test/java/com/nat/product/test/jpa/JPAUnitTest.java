package com.nat.product.test.jpa;

import com.nat.product.model.Product;
import com.nat.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static com.nat.product.model.DiscountManager.*;

@DataJpaTest
public class JPAUnitTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    ProductRepository repository;
    @Test
    public void should_find_no_products_if_repository_is_empty() {
        Iterable products = repository.findAll();
        assertThat(products).isEmpty();
    }
    @Test
    public void should_store_a_product() {
        Product product = repository.save(new Product("Product 1", "Product 1 desc",10.01, TWNETYFIVEPERCENT));
        assertThat(product).hasFieldOrPropertyWithValue("productName", "Product 1");
        assertThat(product).hasFieldOrPropertyWithValue("description", "Product 1 desc");
        assertThat(product).hasFieldOrPropertyWithValue("price", 10.01);
    }
    @Test
    public void should_find_all_products() {
        Product p1 = new Product("Product 1", "Product 1 desc",10.01, TWNETYFIVEPERCENT);
        entityManager.persist(p1);
        Product p2 = new Product("Product 2", "Product 2 desc",12.01, NONE);
        entityManager.persist(p2);
        Product p3 = new Product("Product 3", "Product 3 desc",20.01, BUYONEGETONE);
        entityManager.persist(p3);
        Iterable products = repository.findAll();
        assertThat(products).hasSize(3).contains(p1, p2, p3);
    }
    @Test
    public void should_find_product_by_id() {
        Product p1 = new Product("Product 1", "Product 1 desc",10.01, TWNETYFIVEPERCENT);
        entityManager.persist(p1);
        Product p2 = new Product("Product 2", "Product 2 desc",12.01, NONE);
        entityManager.persist(p2);
        Product foundProduct = repository.findById(p2.getId()).get();
        assertThat(foundProduct).isEqualTo(p2);
    }

    @Test
    public void should_update_product_by_id() {
        Product p1 = new Product("Product 1", "Product 1 desc",10.01, TWNETYFIVEPERCENT);
        entityManager.persist(p1);
        Product p2 = new Product("Product 2", "Product 2 desc",12.01, NONE);
        entityManager.persist(p2);
        Product p2up = new Product("Product 2 updated", "Product 2 desc",50.02, NONE);
        Product p = repository.findById(p2.getId()).get();
        p.setDescription(p2up.getDescription());
        p.setPrice(p2up.getPrice());
        p.setProductName(p2up.getProductName());
        p.setDiscount(p2up.getDiscount());
        repository.save(p);
        Product product = repository.findById(p2.getId()).get();

        assertThat(product.getId()).isEqualTo(p2.getId());
        assertThat(product.getProductName()).isEqualTo(p2up.getProductName());
        assertThat(product.getDescription()).isEqualTo(p2up.getDescription());
        assertThat(product.getPrice()).isEqualTo(p2up.getPrice());
    }
    @Test
    public void should_delete_product_by_id() {
        Product p1 = new Product("Product 1", "Product 1 desc",10.01, TWNETYFIVEPERCENT);
        entityManager.persist(p1);
        Product p2 = new Product("Product 2", "Product 2 desc",12.01, NONE);
        entityManager.persist(p2);
        Product p3 = new Product("Product 3", "Product 3 desc",20.01, BUYONEGETONE);
        entityManager.persist(p3);
        repository.deleteById(p2.getId());
        Iterable products = repository.findAll();
        assertThat(products).hasSize(2).contains(p1, p3);
    }
    @Test
    public void should_delete_all_products() {
        Product p1 = new Product("Product 1", "Product 1 desc",10.01, TWNETYFIVEPERCENT);
        entityManager.persist(p1);
        Product p2 = new Product("Product 2", "Product 2 desc",12.01, NONE);
        entityManager.persist(p2);
        repository.deleteAll();
        assertThat(repository.findAll()).isEmpty();
    }

}
