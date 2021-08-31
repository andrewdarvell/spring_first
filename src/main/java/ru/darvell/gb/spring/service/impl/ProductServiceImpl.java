package ru.darvell.gb.spring.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.repository.ProductRepository;
import ru.darvell.gb.spring.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }


    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public List<Product> getAllProductsFiltered(Map<String, String> filters) {
        return productRepository.getAllProductsFiltered(filters);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
