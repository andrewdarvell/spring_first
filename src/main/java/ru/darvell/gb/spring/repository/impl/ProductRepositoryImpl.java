package ru.darvell.gb.spring.repository.impl;

import org.springframework.stereotype.Repository;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.repository.ProductRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        products.add(new Product(0, "IBM 090: Hollerith Type I Tabulator", BigDecimal.valueOf(10000)));
        products.add(new Product(1, "IBM 091: Hollerith Type III Tabulator", BigDecimal.valueOf(2000)));
        products.add(new Product(2, "IBM 092: Electric Tabulating Machine", BigDecimal.valueOf(40000.99)));
        products.add(new Product(3, "IBM 093: Automatic Control Tabulator", BigDecimal.valueOf(1000)));
        products.add(new Product(4, "Hollerith Type 3-S Tabulator", BigDecimal.valueOf(500)));
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Optional<Product> getById(long id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }
}
