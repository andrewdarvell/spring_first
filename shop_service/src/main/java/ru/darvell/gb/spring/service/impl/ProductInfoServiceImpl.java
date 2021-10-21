package ru.darvell.gb.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.product_type.dto.ProductTypeValueDTO;
import ru.darvell.gb.spring.exception.ShopEntityNotFoundException;
import ru.darvell.gb.spring.service.ProductService;
import ru.darvell.gb.spring.service.primary.ProductInfoService;
import ru.darvell.gb.spring.service.product_type.ProductTypeValueService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductInfoServiceImpl implements ProductInfoService {

    private final ProductTypeValueService productTypeValueService;
    private final ProductService productService;

    @Override
    public List<ProductTypeValueDTO> getAllInfoValuesByProductId(long productId) {
        return getAllInfoValuesByProduct(productService.findById(productId).orElseThrow(() -> new ShopEntityNotFoundException("Продукт не найден")));
    }

    @Override
    public List<ProductTypeValueDTO> getAllInfoValuesByProduct(Product product) {
        return productTypeValueService.getAllByProduct(product).stream().map(ProductTypeValueDTO::new).collect(Collectors.toList());
    }
}
