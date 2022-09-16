package com.nat.product.service;

import com.nat.product.dto.BasketVO;
import com.nat.product.dto.ItemsVO;
import com.nat.product.dto.ProductVO;
import com.nat.product.exception.BasketNotFoundException;

import java.util.List;

public interface BasketService {

    public BasketVO getBasket(long basketId) throws BasketNotFoundException ;
    public BasketVO addProducts(long basketId, List<ItemsVO> items);
    public boolean removeProducts(long basketId, List<ItemsVO> items);

}
