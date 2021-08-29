package ru.darvell.gb.spring.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.dao.ProductDAO;
import ru.darvell.gb.spring.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    public Optional<Product> findById(long id) {
        return productDAO.findById(id);
    }

    @Override
    public void saveOrUpdate(Product product) {
        productDAO.saveOrUpdate(product);
    }
}
