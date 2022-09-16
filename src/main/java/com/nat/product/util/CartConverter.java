package com.nat.product.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nat.product.model.Cart;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CartConverter {
    private static final ObjectMapper mapper = new ObjectMapper();
    public static List<Cart> getCartFromJSON(String products)  {
        try {
            //return mapper.readValue(products, new TypeReference<List<Cart>>(){});
            return mapper.readerForListOf(Cart.class).readValue(products);
        } catch (JsonProcessingException e) {

        }
        return new ArrayList<>();
    }

    public static String getJsonFromCard(List<Cart> items){
        try {
            return mapper.writeValueAsString(items);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
