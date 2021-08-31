package ru.darvell.gb.spring.repository;

import ru.darvell.gb.spring.domain.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductRepositoryCustomSelector {

    List<Product> getAllProductsFiltered(Map<String, String> filters);
}
