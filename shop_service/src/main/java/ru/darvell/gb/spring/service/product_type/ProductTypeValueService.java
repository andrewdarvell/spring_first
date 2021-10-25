package ru.darvell.gb.spring.service.product_type;

import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.product_type.ProductTypeValue;

import java.util.List;
import java.util.Optional;

public interface ProductTypeValueService {

    Optional<ProductTypeValue> getById(long id);
    ProductTypeValue saveAndFlush(ProductTypeValue productTypeValue);
    List<ProductTypeValue> getAllByProduct(Product product);
}
