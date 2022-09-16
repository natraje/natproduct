package com.nat.product.service;

import com.nat.product.dto.ProductVO;
import com.nat.product.exception.ProductNotFoundException;
import com.nat.product.model.Product;

import java.util.List;

public interface ProductService {

    public List<ProductVO> getProducts();
    public ProductVO getProduct(Long id) throws ProductNotFoundException;
    public ProductVO updateProduct(ProductVO productVO) throws ProductNotFoundException;
    public boolean deleteProduct(Long id) throws ProductNotFoundException;
    public ProductVO createProduct(ProductVO productVO);
    public void deleteAll();
}
