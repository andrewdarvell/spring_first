package ru.darvell.gb.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.darvell.gb.spring.domain.FilterProductRequest;
import ru.darvell.gb.spring.domain.Product;


public interface ProductRepositoryCustomSelector {

    Page<Product> getAllProductsFiltered(FilterProductRequest filterProductRequest, Pageable pageable);
}
