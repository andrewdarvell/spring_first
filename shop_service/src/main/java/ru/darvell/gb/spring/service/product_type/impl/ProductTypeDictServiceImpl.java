package ru.darvell.gb.spring.service.product_type.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.product_type.DictValueType;
import ru.darvell.gb.spring.domain.product_type.ProductType;
import ru.darvell.gb.spring.domain.product_type.ProductTypeDict;
import ru.darvell.gb.spring.repository.product_type.ProductTypeDictRepository;
import ru.darvell.gb.spring.service.product_type.ProductTypeDictService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductTypeDictServiceImpl implements ProductTypeDictService {

    private final ProductTypeDictRepository productTypeDictRepository;

    @Override
    public Optional<ProductTypeDict> getById(long id) {
        return productTypeDictRepository.findById(id);
    }

    @Override
    public ProductTypeDict saveAndFlush(ProductTypeDict productTypeDict) {
        return productTypeDictRepository.saveAndFlush(productTypeDict);
    }

    @Override
    @Transactional
    public List<ProductTypeDict> saveAndFlushBulk(List<ProductTypeDict> productTypeDicts) {
        return productTypeDictRepository.saveAll(productTypeDicts);
    }

    @Override
    public List<ProductTypeDict> getAllByProductType(ProductType productType) {
        return productTypeDictRepository.findAllByProductType(productType);
    }

    @Override
    public List<ProductTypeDict> getAllByDictValueType(DictValueType dictValueType) {
        return productTypeDictRepository.findAllByDictValueType(dictValueType);
    }
}
