package ru.darvell.gb.spring.service.product_type.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.product_type.ProductTypeValue;
import ru.darvell.gb.spring.repository.product_type.ProductTypeValueRepository;
import ru.darvell.gb.spring.service.product_type.ProductTypeValueService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductTypeValueServiceImpl implements ProductTypeValueService {

    private final ProductTypeValueRepository productTypeValueRepository;

    @Override
    public Optional<ProductTypeValue> getById(long id) {
        return productTypeValueRepository.findById(id);
    }

    @Override
    public ProductTypeValue saveAndFlush(ProductTypeValue productTypeValue) {
        return productTypeValueRepository.saveAndFlush(productTypeValue);
    }

    @Override
    public List<ProductTypeValue> getAllByProduct(Product product) {
        return productTypeValueRepository.getAllByProductAndProductType(product, product.getProductType());
    }
}
