package ru.darvell.gb.spring.dao;

import ru.darvell.gb.spring.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {

    List<Product> getAll();
    Optional<Product> findById(long id);
    Product saveOrUpdate(Product product);
    void deleteById(long id);
}
