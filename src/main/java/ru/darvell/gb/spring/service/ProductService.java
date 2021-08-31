package ru.darvell.gb.spring.service;

import ru.darvell.gb.spring.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {

    List<Product> getAll();
    Optional<Product> findById(Long id);
    Product saveOrUpdate(Product product);
    List<Product> getAllProductsFiltered(Map<String, String> filters);
    void deleteProduct(Product product);
}
