package com.nat.product.service;


import com.nat.product.dto.ProductVO;
import com.nat.product.exception.ProductNotFoundException;
import com.nat.product.model.Product;
import com.nat.product.repository.ProductRepository;
import com.nat.product.task.CreateProductTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductVO> getProducts() {
        List<Product> products=productRepository.findAll();
        return productRepository.findAll().stream().map(post -> modelMapper.map(post, ProductVO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductVO getProduct(Long id) throws ProductNotFoundException {
        return modelMapper.map(productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id)),ProductVO.class);
    }

    @Override
    public ProductVO updateProduct(ProductVO productVO) throws ProductNotFoundException {
        Optional<Product> ProductData = productRepository.findById(productVO.getId());
        if (ProductData.isPresent())
        {
            Product productNew = modelMapper.map(productVO,Product.class);
            productRepository.save(productNew);
            return productVO;
        } else {
            throw new ProductNotFoundException(productVO.getId());
        }
    }

    @Override
    public boolean deleteProduct(Long id) throws ProductNotFoundException {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public ProductVO createProduct(ProductVO productVO) {
        Product productNew = modelMapper.map(productVO,Product.class);
        productNew=productRepository.save(productNew);
        ProductVO vo=modelMapper.map(productNew,ProductVO.class);
        return vo;
    }

    @Override
    public List<ProductVO> createProduct(List<ProductVO> products) {

        return CreateProductTask.startForkJoinSum(products,productRepository,modelMapper);
    }

    public void deleteAll(){
        productRepository.deleteAll();
    }
}
