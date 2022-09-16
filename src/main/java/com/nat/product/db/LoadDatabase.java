package com.nat.product.db;

import com.nat.product.model.Product;
import com.nat.product.model.ProductDiscount;
import com.nat.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

//    @Bean
//    CommandLineRunner initDatabase(ProductRepository repository) {
//
//        return args -> {
//            log.info("Preloading " + repository.save(new Product("Product 1", "Product 1 desc",10.01, ProductDiscount.NONE)));
//            log.info("Preloading " + repository.save(new Product("Product 2", "Product 2 desc",100.00, ProductDiscount.BUYONEGETONE)));
//        };
//    }
}