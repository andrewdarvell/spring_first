package ru.darvell.gb.spring.repository;

import ru.darvell.gb.spring.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> getAll();
    Optional<Product> findById(long id);
    void addProduct(Product product);
}
