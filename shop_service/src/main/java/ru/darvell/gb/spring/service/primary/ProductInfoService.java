package ru.darvell.gb.spring.service.primary;

import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.product_type.dto.ProductTypeValueDTO;

import java.util.List;

public interface ProductInfoService {

    List<ProductTypeValueDTO> getAllInfoValuesByProductId(long productId);
    List<ProductTypeValueDTO> getAllInfoValuesByProduct(Product product);
}
