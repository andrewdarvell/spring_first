package ru.darvell.gb.spring.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.repository.ProductRepository;
import ru.darvell.gb.spring.service.ProductService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    public ProductRepository productRepository;

    public ProductService productService;

    public Product expectedProduct;

    @BeforeEach
    public void setUp() {
        expectedProduct = new Product(1L, "test", new BigDecimal("10.0"));
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void shouldReturnOptionalProductOnGetById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(expectedProduct));
        Optional<Product> actualOptionalProduct = productService.findById(1L);
        Assertions.assertTrue(actualOptionalProduct.isPresent());
        Product actualProduct = actualOptionalProduct.get();
        Assertions.assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void shouldReturnListOnGetAll() {
        when(productRepository.getAll()).thenReturn(Collections.singletonList(expectedProduct));
        List<Product> products = productService.getAll();
        Assertions.assertNotNull(products);
        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals(expectedProduct, products.get(0));
    }

}
