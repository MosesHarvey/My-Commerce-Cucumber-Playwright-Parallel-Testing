package com.mycommerce.appdata;

import java.lang.reflect.Array;
import java.util.List;

public class AppConstant {

    public static final String HOME_PAGE_TITLE = "Automation Exercise";
    public static final String LOGIN_PAGE_TITLE = "Automation Exercise - Signup / Login";
    public static final String PRODUCT_PAGE_TITLE = "Automation Exercise - All Products";
    public static final String PRODUCT_DETAIL_PAGE_TITLE = "Automation Exercise - Product Details";
    public static final String FILE_PATH = "src/test/resources/Simple Grocery Store API Documentation.pdf";
    public static final String SEARCH_TERM = "jeans";
    public static final String PRODUCT_SEARCH_URL = "https://www.automationexercise.com/products?search="+SEARCH_TERM;
    public static final String EMPTY_CART_TEXT = "Cart is empty! Click here to buy products.";
    public static final List<String> CATEGORY =List.of("Women", "Men", "Kids");
    public static final List<String> WOMEN_CATEGORY =List.of("Dress", "Tops", "Saree");
    public static final List<String> MEN_CATEGORY = List.of("TShirts", "Jeans");
    public static final List<String> KIDS_CATEGORY = List.of("Dress", "Tops & Shirts ");


}
