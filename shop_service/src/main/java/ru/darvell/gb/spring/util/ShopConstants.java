package ru.darvell.gb.spring.util;

public class ShopConstants {

    public static final String REST_URL_V1 = "/api/v1";
    public static final String PRODUCT_URL = "/product";
    public static final String CATEGORY_URL = "/category";
    public static final String USER_URL = "/user";
    public static final String ADMIN_URL = "/admin";

    public static final String IMAGE_UPLOAD_LINK_PATTERN_V1 = REST_URL_V1 + ADMIN_URL + PRODUCT_URL + "/%d/image";

    private ShopConstants() {
    }
}