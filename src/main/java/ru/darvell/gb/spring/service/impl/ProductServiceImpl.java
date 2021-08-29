package ru.darvell.gb.spring.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.repository.ProductRepository;
import ru.darvell.gb.spring.service.ProductService;

import java.util.List;
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
    public List<Product> getAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public void saveOrUpdate(Product product) {
        productRepository.saveAndFlush(product);
    }
}
