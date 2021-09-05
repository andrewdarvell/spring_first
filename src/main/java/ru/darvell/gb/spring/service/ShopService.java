package ru.darvell.gb.spring.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.domain.FilterProductRequest;
import ru.darvell.gb.spring.domain.dto.CategoryDTO;
import ru.darvell.gb.spring.domain.dto.ProductDTO;
import ru.darvell.gb.spring.domain.dto.ProductRestDTO;
import ru.darvell.gb.spring.exception.ShopEntityNotFoundException;
import ru.darvell.gb.spring.exception.ShopException;

import java.util.List;

public interface ShopService {

    ProductDTO saveOrUpdateProduct(ProductDTO productDTO) throws ShopException;
    ProductDTO saveWithImage(ProductDTO productDTO, MultipartFile image);
    void addImageToProduct(Long productId, MultipartFile image) throws ShopEntityNotFoundException;
    ProductRestDTO saveProduct(ProductRestDTO productRestDTO) throws ShopEntityNotFoundException;
    ProductRestDTO updateProduct(ProductRestDTO productRestDTO) throws ShopEntityNotFoundException;
    ProductDTO getProductByIdOrEmpty(Long productId);
    ProductRestDTO getProductByIdForRest(Long productId) throws ShopEntityNotFoundException;
    Page<ProductRestDTO> getAllProductsPageable(FilterProductRequest filterProductRequest);

    void deleteProductByID(Long productId);

    List<CategoryDTO> getAllCategories();
    Category saveOrUpdateCategory(CategoryDTO categoryDTO) throws ShopException;
}
