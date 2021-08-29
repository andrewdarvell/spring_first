package ru.darvell.gb.spring.repository;

import ru.darvell.gb.spring.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepositoryCustomSelector {

    List<Product> getAllProductsFilterByCostAndTitle(BigDecimal minCost, BigDecimal maxCost, String title);
}
