package com.nat.product.controller;

import com.nat.product.dto.BasketVO;
import com.nat.product.dto.ItemsVO;
import com.nat.product.dto.ProductVO;

import com.nat.product.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Min;
import java.util.List;

import static com.nat.product.util.ApplicationConstants.*;

@RestController
@RequestMapping(API)
public class BasketController {
    @Autowired
    private BasketService basketService;

    @PostMapping(BASKETS_BASKET_ID)
    public ResponseEntity<BasketVO> add(@PathVariable("basketId") long basketId,@RequestBody List<ItemsVO> items) {
        try {
            return new ResponseEntity<>(basketService.addProducts(basketId,items), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/baskets/{basketId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("basketId") long basketId,@RequestBody List<ItemsVO> items) {
        try {
            basketService.removeProducts(basketId,items);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/baskets/{basketId}")
    public ResponseEntity<BasketVO> get(@PathVariable("basketId") long basketId) {
        return new ResponseEntity<BasketVO>(basketService.getBasket(basketId),HttpStatus.OK);
    }
}
