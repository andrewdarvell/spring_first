package ru.darvell.gb.spring.service.soap;

import ru.darvell.gb.spring.ws.soap.products.Product;

import java.util.List;

public interface ProductSoapService {

    List<Product> getAllProducts();
}
