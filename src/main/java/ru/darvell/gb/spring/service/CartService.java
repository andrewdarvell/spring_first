package ru.darvell.gb.spring.service;


import ru.darvell.gb.spring.domain.Product;

import java.util.List;

public interface CartService {

    boolean addProduct(long id);
    boolean removeProduct(long id);
    List<Product> getAllProductsInCart();
}
