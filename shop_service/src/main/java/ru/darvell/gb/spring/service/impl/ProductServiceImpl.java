package ru.darvell.gb.spring.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.nonpersist.FilterProductRequest;
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
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> getAllByIds(List<Long> ids) {
        return productRepository.findAllByIdIn(ids);
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
    public Page<Product> getAllProductsFiltered(FilterProductRequest filterProductRequest, Pageable pageable) {
        return productRepository.getAllProductsFiltered(filterProductRequest, pageable);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
