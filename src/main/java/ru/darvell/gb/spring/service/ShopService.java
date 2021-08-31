package ru.darvell.gb.spring.service;

import org.springframework.web.multipart.MultipartFile;
import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.domain.dto.CategoryDTO;
import ru.darvell.gb.spring.domain.dto.ProductDTO;
import ru.darvell.gb.spring.exception.ShopException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ShopService {

    ProductDTO saveOrUpdateProduct(ProductDTO productDTO) throws ShopException;
    ProductDTO getProductByIdOrEmpty(Long productId);
    List<ProductDTO> getAllProducts(Map<String, String> filters);
    ProductDTO saveWithImage(ProductDTO productDTO, MultipartFile image);

    void deleteProductByID(Long productId);

    List<CategoryDTO> getAllCategories();
    Category saveOrUpdateCategory(CategoryDTO categoryDTO) throws ShopException;
}
