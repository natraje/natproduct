package com.nat.product.controller;

import com.nat.product.exception.ProductNotFoundException;
import com.nat.product.model.Product;
import com.nat.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v0")
public class ProductControllerV00 {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductVOAssembler assembler;


    @GetMapping("/products")
    public CollectionModel<EntityModel<Product>> all() {
        try {
            List<Product> products =productRepository.findAll();
            List<EntityModel<Product>> productCollection = products.stream().map(assembler::toModel).collect(Collectors.toList());
//            if (products.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(products, HttpStatus.OK);
            return CollectionModel.of(productCollection, linkTo(methodOn(ProductControllerV00.class).all()).withSelfRel());
        } catch (Exception e) {
            //return new CollectionModel<EntityModel<Product>>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            return null;
        }
    }


    @GetMapping("/products/{id}")
    public EntityModel<Product> one(@PathVariable Long id) {
        Product product= productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return assembler.toModel(product);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> add(@RequestBody Product product) {
        try {
            Product _Product = productRepository
                    .save(new Product(product.getProductName(),product.getDescription(),product.getPrice(),product.getDiscount()));
            return new ResponseEntity<>(_Product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        Optional<Product> ProductData = productRepository.findById(id);
        if (ProductData.isPresent()) {
            Product productNew = ProductData.get();
            productNew.setProductName(product.getProductName());
            productNew.setDescription(product.getDescription());
            productNew.setDiscount(product.getDiscount());
            productNew.setPrice(product.getPrice());
            return new ResponseEntity<>(productRepository.save(productNew), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/products")
    public ResponseEntity<HttpStatus> deleteAllProducts() {
        try {
            productRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
