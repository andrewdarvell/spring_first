package ru.darvell.gb.spring.service;

import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAll();
    List<Product> getAllByCategory(Category category);
    Optional<Product> findById(Long id);
    Product saveOrUpdate(Product product);
    List<Product> getAllProductsFilterByCostAndTitle(BigDecimal minCost, BigDecimal maxCost, String title);
}
