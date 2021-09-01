package ru.darvell.gb.spring.repository;

import org.springframework.data.domain.Pageable;
import ru.darvell.gb.spring.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepositoryCustomSelector {

    List<Product> getAllProductsFiltered(Map<String, String> filters);
    List<Product> getAllProductsFiltered(Map<String, String> filters, Pageable pageable);
}
