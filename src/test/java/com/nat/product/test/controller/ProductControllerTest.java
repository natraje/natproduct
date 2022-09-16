package com.nat.product.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nat.product.controller.ProductController;
import com.nat.product.dto.ProductVO;
import com.nat.product.exception.ProductNotFoundException;
import com.nat.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

import static com.nat.product.model.DiscountManager.TWNETYFIVEPERCENT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.nat.product.util.ApplicationConstants.*;
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void shouldCreateProductVO() throws Exception {
        ProductVO product = new ProductVO("Product 1", "Product 1 desc",10.01, TWNETYFIVEPERCENT);
        mockMvc.perform(post(API_PRODUCTS).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andDo(print());
    }
    @Test
    void shouldReturnListOfProductVOs() throws Exception {
        List<ProductVO> products = new ArrayList<>(
                Arrays.asList(new ProductVO("Product 1", "Product 1 desc",10.01, TWNETYFIVEPERCENT),
                        new ProductVO("Product 2", "Product 2 desc",20.01, TWNETYFIVEPERCENT),
                        new ProductVO("Product 3", "Product 3 desc",30.01, TWNETYFIVEPERCENT)));
        when(productService.getProducts()).thenReturn(products);
        mockMvc.perform(get(API_PRODUCTS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(products.size()))
                .andDo(print());
    }
    @Test
    void shouldReturnNotFoundProductVO() throws Exception {
        long id = 1L;
        when(productService.getProduct(id)).thenThrow(new ProductNotFoundException(id));
        mockMvc.perform(get(API_PRODUCTS_ID, id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    void shouldReturnProductVO() throws Exception {
        long id = 1L;
        ProductVO product = new ProductVO(1L,"Product 1", "Product 1 desc",10.01, TWNETYFIVEPERCENT);
        when(productService.getProduct(id)).thenReturn(product);
        mockMvc.perform(get(API_PRODUCTS_ID, id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.productName").value(product.getProductName()))
                .andExpect(jsonPath("$.description").value(product.getDescription()))
                .andExpect(jsonPath("$.price").value(product.getPrice()))
                .andDo(print());
    }

    @Test
    void shouldUpdateProductVO() throws Exception {
        long id = 1L;
        ProductVO product = new ProductVO(id,"Product 1", "Product 1 desc",10.01, TWNETYFIVEPERCENT);
        ProductVO updatedproduct = new ProductVO(id,"Updated", "Updated", 10.01, TWNETYFIVEPERCENT);
        when(productService.getProduct(id)).thenReturn(product);
        updatedproduct.setId(product.getId());
        when(productService.updateProduct(any(ProductVO.class))).thenReturn(updatedproduct);
        mockMvc.perform(put(API_PRODUCTS_ID, id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedproduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value(updatedproduct.getProductName()))
                .andExpect(jsonPath("$.description").value(updatedproduct.getDescription()))
                .andExpect(jsonPath("$.price").value(updatedproduct.getPrice()))
                .andDo(print());
    }

    @Test
    void shouldReturnNotFoundUpdateProductVO() throws Exception {
        long id = 1L;
        ProductVO updatedproduct = new ProductVO("Updated", "Updated", 10.01, TWNETYFIVEPERCENT);
        ProductVO product =new ProductVO();
        when(productService.getProduct(id)).thenReturn(product);
        when(productService.updateProduct(any(ProductVO.class))).thenReturn(updatedproduct);
        mockMvc.perform(put(API_PRODUCTS_ID, id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedproduct)))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
