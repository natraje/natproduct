package com.nat.product.model;

public enum ProductDiscount {
    BUYONEGETONE(50),
    TWNETYFIVEPERCENT(25),
    TENPERCENT(10),
    NONE(0);

    private int discount;
    ProductDiscount(int v){
        discount=v;
    }

    int getDiscount(){
        return discount;
    }

}
