package com.nat.product.controller;

import com.nat.product.dto.ProductVO;
import com.nat.product.exception.ProductNotFoundException;
import com.nat.product.model.Product;
import com.nat.product.repository.ProductRepository;
import com.nat.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static com.nat.product.util.ApplicationConstants.*;

@RestController
@RequestMapping(API)
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(PRODUCTS)
    public ResponseEntity<List<ProductVO>> all() {
        try {
            List<ProductVO> products=productService.getProducts();
            if (products.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(PRODUCTS_ID)
    public ResponseEntity<ProductVO> one(@PathVariable @Min(1) Long id) {
        return new ResponseEntity<ProductVO>(productService.getProduct(id),HttpStatus.OK);
    }

    @PostMapping(PRODUCTS)
    public ResponseEntity<ProductVO> add(@RequestBody ProductVO product) {
        try {
            return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(PRODUCTS_ID)
    public ResponseEntity<ProductVO> updateProduct(@PathVariable ("id") long id, @RequestBody ProductVO product) {
        product.setId(id);
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);

    }

    @DeleteMapping(PRODUCTS_ID)
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") @Min(1) long id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(PRODUCTS)
    public ResponseEntity<HttpStatus> deleteAllProducts() {
        try {
            productService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
