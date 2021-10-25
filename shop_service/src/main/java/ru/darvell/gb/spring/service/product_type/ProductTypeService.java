package ru.darvell.gb.spring.service.product_type;

import ru.darvell.gb.spring.domain.product_type.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductTypeService {

    Optional<ProductType> getById(long id);
    ProductType saveAndFlush(ProductType productType);
    List<ProductType> getAll();
    List<ProductType> getAllFilteredByTitle(String title);
}
