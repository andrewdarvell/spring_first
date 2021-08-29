package ru.darvell.gb.spring.service;

import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAll();
    List<Product> getAllByCategory(Category category);
    Optional<Product> findById(long id);
    void saveOrUpdate(Product product);
}
