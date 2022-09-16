package com.nat.product.service;

import com.nat.product.dto.BasketVO;
import com.nat.product.dto.ItemsVO;
import com.nat.product.dto.ProductVO;
import com.nat.product.exception.BasketNotFoundException;
import com.nat.product.model.Basket;
import com.nat.product.model.Cart;
import com.nat.product.model.DiscountManager;
import com.nat.product.repository.BasketRepository;
import com.nat.product.util.CartConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static com.nat.product.util.ApplicationConstants.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl implements BasketService{
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }

    @Value("${external.server.url}")
    String externalServerURL;

    public BasketVO getBasket(long basketId) throws BasketNotFoundException {
        Optional<Basket> optionalBasket=basketRepository.findById(basketId);
        if(optionalBasket.isPresent()){
            return getBasketVO(basketId,optionalBasket.get(),true);
        } else{
            throw new BasketNotFoundException(basketId);
        }
    }

    public BasketVO addProducts(long basketId, List<ItemsVO> items) {
        Optional<Basket> optionalBasket=basketRepository.findById(basketId);
        Basket basket=new Basket();
        List<Cart> dtos = items
                .stream()
                .map(item -> {
                    ProductVO vo= restTemplate.getForObject(externalServerURL+API_PRODUCTS+"/"+item.getProductId(), ProductVO.class, item.getProductId());
                   // ProductVO vo=new ProductVO();
                    Cart cart=modelMapper.map(item, Cart.class);
                    cart.setProductName(vo.getProductName());
                    cart.setPrice(vo.getPrice());
                    cart.setDiscount(vo.getDiscount());
                    return cart;
                })
                .collect(Collectors.toList());
        if(optionalBasket.isPresent()) {
            basket=optionalBasket.get();
            List<Cart> currItems= CartConverter.getCartFromJSON(optionalBasket.get().getProducts());
            currItems.addAll(dtos);
            String itemString=CartConverter.getJsonFromCard(currItems);
            basket.setProducts(itemString);
        } else{
            basket.setBasketId(basketId);
            String itemString=CartConverter.getJsonFromCard(dtos);
            basket.setProducts(itemString);
        }
        basketRepository.save(basket);
        return getBasketVO(basket.getBasketId(),basket,false);
    }

    @Override
    public boolean removeProducts(long basketId, List<ItemsVO> items) {
        Optional<Basket> optionalBasket=basketRepository.findById(basketId);
        if(optionalBasket.isPresent()) {
            Basket basket=optionalBasket.get();
            List<Cart> currItems= CartConverter.getCartFromJSON(basket.getProducts());
            Set<Long> productIds=items.stream().map(ItemsVO::getProductId).collect(Collectors.toSet());
            List<Cart> dtosNew=currItems.stream().filter(d-> !productIds.contains(d.getProductId())).collect(Collectors.toList());
            String itemString=CartConverter.getJsonFromCard(dtosNew);
            basket.setProducts(itemString);
            basketRepository.save(basket);
            return true;
        }
        return false;
    }

    private BasketVO getBasketVO(long basketId,Basket basket, boolean isTotal){
        List<Cart> items= CartConverter.getCartFromJSON(basket.getProducts());
        BasketVO vo=new BasketVO();
        vo.setBasketId(basketId);
        List<ItemsVO> itemVOs = items.stream().map(m-> {
            ItemsVO vo1= modelMapper.map(m,ItemsVO.class);
            return vo1;
        }).collect(Collectors.toList());;
        vo.setItems(itemVOs);
        if(isTotal){
        double total=items.stream().map(c->
                DiscountManager.getFinalPrice(c.getQuantity(),c.getPrice(),c.getDiscount())).reduce(0.0, Double::sum);
        vo.setTotal(total);
        }
        return vo;
    }
}
