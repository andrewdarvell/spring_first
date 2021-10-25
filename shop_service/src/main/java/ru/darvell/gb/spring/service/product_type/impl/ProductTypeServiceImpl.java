package ru.darvell.gb.spring.service.product_type.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.product_type.ProductType;
import ru.darvell.gb.spring.repository.product_type.ProductTypeRepository;
import ru.darvell.gb.spring.service.product_type.ProductTypeService;

@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    @Override
    public ProductType saveAndFlush(ProductType productType) {
        return productTypeRepository.saveAndFlush(productType);
    }
}
