package ru.darvell.gb.spring.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.darvell.gb.spring.controller.ProductController;
import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.dto.CategoryDTO;
import ru.darvell.gb.spring.domain.dto.ProductDTO;
import ru.darvell.gb.spring.exception.ShopException;
import ru.darvell.gb.spring.service.CategoryService;
import ru.darvell.gb.spring.service.ProductService;
import ru.darvell.gb.spring.service.ShopService;
import ru.darvell.gb.spring.util.FileUtils;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.darvell.gb.spring.service.ShopConstants.KEY_CATEGORY_ID;
import static ru.darvell.gb.spring.service.ShopConstants.KEY_CATEGORY_NAME_FILTER;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopServiceImpl implements ShopService {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final Validator validator;

    @Override
    public ProductDTO saveOrUpdateProduct(ProductDTO productDTO) throws ShopException {
        Product product = new Product(productDTO);
        Category category = categoryService.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new ShopException("Категория не найдена"));

        product.setCategory(category);
        checkShopEntity(product);

        return new ProductDTO(productService.saveOrUpdate(product));
    }


    @Override
    public ProductDTO getProductByIdOrEmpty(Long productId) {
        if (productId != null) {
            return new ProductDTO(productService.findById(productId).orElse(new Product()));
        } else {
            return new ProductDTO(new Product());
        }
    }


    @Override
    public List<ProductDTO> getAllProducts(Map<String, String> filters) {
        if (filters.isEmpty()) {
            return productService.getAll().stream().map(ProductDTO::new).collect(Collectors.toList());
        }

        String categoryTitle = filters.get(KEY_CATEGORY_NAME_FILTER);
        categoryService.findByTitle(categoryTitle).ifPresent(c -> filters.put(KEY_CATEGORY_ID, String.valueOf(c.getId())));

        return productService.getAllProductsFiltered(filters)
                .stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductDTO saveWithImage(ProductDTO productDTO, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            Path pathImage = FileUtils.saveProductImage(image);
            productDTO.setImageLink(pathImage.toString());
        }
        return saveOrUpdateProduct(productDTO);
    }

    @Override
    public void deleteProductByID(Long productId) {
        log.info("product {}", productService.findById(productId).isPresent());
        productService.findById(productId).ifPresent(productService::deleteProduct);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAll().stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    @Override
    public Category saveOrUpdateCategory(CategoryDTO categoryDTO) throws ShopException {
        Category category = new Category(categoryDTO);
        checkShopEntity(category);
        return categoryService.saveOrUpdate(new Category(categoryDTO));
    }


    private <T> void checkShopEntity(T t) throws ShopException {
        String errorsString = validator.validate(t).stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));
        if (!errorsString.isEmpty()) {
            throw new ShopException(errorsString);
        }
    }

}
