package ru.darvell.gb.spring.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.dto.CategoryDTO;
import ru.darvell.gb.spring.domain.dto.ProductDTO;
import ru.darvell.gb.spring.exception.ShopProductException;
import ru.darvell.gb.spring.service.CategoryService;
import ru.darvell.gb.spring.service.ProductService;
import ru.darvell.gb.spring.service.ShopProductService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopProductServiceImpl implements ShopProductService {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final Validator validator;

    @Override
    public ProductDTO saveOrUpdateProduct(ProductDTO productDTO) throws ShopProductException {
        Product product = new Product(productDTO);
        Category category = categoryService.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new ShopProductException("Категория не найдена"));

        product.setCategory(category);

        String errors = checkProduct(product);
        if (!errors.isEmpty()) {
            throw new ShopProductException(errors);
        }
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
    public List<ProductDTO> getAllProducts() {
        return productService.getAll().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    private List<ProductDTO> getAllProductsByCategory(Category category) {
        return productService.getAllByCategory(category).stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getAllProductsFilteredByCategoryTitle(String categoryTitle) {
        Optional<Category> categoryOptional = categoryService.findByTitle(categoryTitle);
        if (categoryOptional.isPresent()) {
            return getAllProductsByCategory(categoryOptional.get());
        } else {
            return getAllProducts();
        }

    }

    @Override
    public List<ProductDTO> getAllProductsFilterByCostAndTitle(BigDecimal minCost, BigDecimal maxCost, String title) {
        return productService.getAllProductsFilterByCostAndTitle(minCost, maxCost, title).stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAll().stream().map(CategoryDTO::new).collect(Collectors.toList());
    }


    public String checkProduct(Product product) {
        return validator.validate(product).stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));
    }
}
