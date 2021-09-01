package ru.darvell.gb.spring.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.multipart.MultipartFile;
import ru.darvell.gb.spring.MVCTestConfig;
import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.dto.CategoryDTO;
import ru.darvell.gb.spring.domain.dto.ProductDTO;
import ru.darvell.gb.spring.exception.ShopException;
import ru.darvell.gb.spring.service.CategoryService;
import ru.darvell.gb.spring.service.ProductService;
import ru.darvell.gb.spring.service.ShopService;
import ru.darvell.gb.spring.util.FileUtils;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Optional;

import static org.mockito.Mockito.*;


class ShopServiceImplTest extends MVCTestConfig {

    @MockBean
    public ProductService productService;

    @MockBean
    public CategoryService categoryService;

    @MockBean
    public FileUtils fileUtils;

    @Autowired
    public Validator validator;

    public ShopService shopService;

    public Product expectedProduct;
    public Category expectedCategory;

    @BeforeEach
    public void setUp() {
        expectedCategory = Category.builder()
                .id(1L)
                .title("test")
                .build();

        expectedProduct = Product.builder()
                .id(1L)
                .title("test")
                .cost(new BigDecimal("10.0"))
                .category(expectedCategory)
                .build();
        shopService = new ShopServiceImpl(productService, categoryService, validator);


        when(productService.saveOrUpdate(Mockito.any())).thenReturn(expectedProduct);
        when(productService.findById(1L)).thenReturn(Optional.of(expectedProduct));
        when(categoryService.findById(1L)).thenReturn(Optional.of(expectedCategory));
    }

    @Test
    void shouldSaveProductValidateData() {
        expectedProduct.setTitle(null);
        ProductDTO productDTO = new ProductDTO(expectedProduct);
        Assertions.assertThrows(ShopException.class, () -> shopService.saveOrUpdateProduct(productDTO));
    }

    @Test
    void shouldSaveProductWithImageValidateData() {
        expectedProduct.setTitle(null);
        ProductDTO productDTO = new ProductDTO(expectedProduct);
        MultipartFile multipartFile = Mockito.mock(MultipartFile.class);
        Path path = Mockito.mock(Path.class);
        try (MockedStatic<FileUtils> fileUtilsMocked = Mockito.mockStatic(FileUtils.class)) {
            fileUtilsMocked.when(() -> FileUtils.saveProductImage(multipartFile)).thenReturn(path);
            Assertions.assertThrows(ShopException.class, () -> shopService.saveWithImage(productDTO, multipartFile));
        }
    }

    @Test
    void shouldSaveCategoryValidateData() {
        expectedCategory.setTitle(null);
        CategoryDTO categoryDTO = new CategoryDTO(expectedCategory);
        Assertions.assertThrows(ShopException.class, () -> shopService.saveOrUpdateCategory(categoryDTO));
    }

    @Test
    void shouldDeleteByIdCorrect() {
        shopService.deleteProductByID(1L);
        verify(productService, times(1)).deleteProduct(expectedProduct);
    }
}
