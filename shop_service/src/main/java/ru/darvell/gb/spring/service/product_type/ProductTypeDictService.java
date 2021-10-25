package ru.darvell.gb.spring.service.product_type;

import ru.darvell.gb.spring.domain.product_type.DictValueType;
import ru.darvell.gb.spring.domain.product_type.ProductType;
import ru.darvell.gb.spring.domain.product_type.ProductTypeDict;

import java.util.List;
import java.util.Optional;

public interface ProductTypeDictService {

    Optional<ProductTypeDict> getById(long id);
    ProductTypeDict saveAndFlush(ProductTypeDict productTypeDict);
    List<ProductTypeDict> getAllByProductType(ProductType productType);
    List<ProductTypeDict> getAllByDictValueType(DictValueType dictValueType);

}
