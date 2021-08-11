package ru.darvell.gb.spring.repository.impl;

import org.springframework.stereotype.Repository;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.repository.ProductRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products = new LinkedList<>();

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Optional<Product> findById(long id) {
        return products.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public void addProduct(Product product) {
        product.setId((getMaxId().orElse(0L)) + 1);
        products.add(product);
    }

    private Optional<Long> getMaxId() {
        return products.stream().map(Product::getId).max((Long::compare));
    }
}
