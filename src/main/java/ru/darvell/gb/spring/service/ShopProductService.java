package ru.darvell.gb.spring.service;

import ru.darvell.gb.spring.domain.dto.CategoryDTO;
import ru.darvell.gb.spring.domain.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ShopProductService {

    ProductDTO saveOrUpdateProduct(ProductDTO productDTO);
    ProductDTO getProductByIdOrEmpty(Long productId);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getAllProductsFilteredByCategoryTitle(String categoryTitle);
    List<ProductDTO> getAllProductsFilterByCostAndTitle(BigDecimal minCost, BigDecimal maxCost, String title);

    List<CategoryDTO> getAllCategories();
}
