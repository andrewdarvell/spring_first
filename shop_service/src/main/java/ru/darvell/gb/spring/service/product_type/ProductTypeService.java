package ru.darvell.gb.spring.service.product_type;

import ru.darvell.gb.spring.domain.product_type.ProductType;

public interface ProductTypeService {

    ProductType saveAndFlush(ProductType productType);
}
