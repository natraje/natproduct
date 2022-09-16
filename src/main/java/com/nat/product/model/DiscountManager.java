package com.nat.product.model;

import java.util.HashMap;
import java.util.Map;

public class DiscountManager {

    public static final String BUYONEGETONE = "BUYONEGETONE";
    public static final String TENPERCENT = "TEN";
    public static final String TWNETYFIVEPERCENT = "TWENTYFIVE";
    public static final String NONE = "NONE";

    private static final Map<String,Integer> discountMap=new HashMap<>();
    static{
        discountMap.put(BUYONEGETONE,50);
        discountMap.put(TENPERCENT,10);
        discountMap.put(TWNETYFIVEPERCENT,25);
        discountMap.put(NONE,0);
    }


  public static int getDiscountPercentage(String discount){
      return discountMap.getOrDefault(discount.toUpperCase(),0);
  }

  public static boolean isDiscountValid(int val){
      return discountMap.containsValue(val);
  }

  public static double getFinalPrice(int quantity, double price, String discount){
        return (quantity*price*(100-discountMap.getOrDefault(discount,0))/100);
  }

}
