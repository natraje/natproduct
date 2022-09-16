package com.nat.product.controller;

import com.nat.product.model.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductVOAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

    @Override
    public EntityModel<Product> toModel(Product product) {

        return EntityModel.of(product,
                linkTo(methodOn(ProductControllerV00.class).one(product.getId())).withSelfRel(),
                linkTo(methodOn(ProductControllerV00.class).all()).withRel("products"));
    }
}
