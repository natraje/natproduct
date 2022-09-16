package com.nat.product.test.mockdata;

import com.nat.product.model.Product;

import java.util.ArrayList;
import java.util.List;

import static com.nat.product.model.DiscountManager.*;

public class ProductMockDataGenerator {

    private static final List<Product> products=new ArrayList<>();
    static{
        products.add(new Product("Product 1", "Product 1 desc",10.01, TWNETYFIVEPERCENT));
        products.add(new Product("Product 2", "Product 2 desc",12.01, NONE));
        products.add(new Product("Product 3", "Product 3 desc",20.01, BUYONEGETONE));
        products.add(new Product("Product 2 updated", "Product 2 desc",50.02, NONE));
    }

    public static Product getMockProduct(int idx ){
        return products.get(idx);
    }
}
