package ru.darvell.gb.spring.controller;


import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import ru.darvell.gb.spring.MVCTestConfig;
import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.dto.CategoryDTO;
import ru.darvell.gb.spring.domain.dto.ProductDTO;
import ru.darvell.gb.spring.service.ShopService;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest extends MVCTestConfig {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public ShopService shopService;

    public Product expectedProduct;
    public Category expectedCategory;

    @BeforeEach
    public void setUp() {
        expectedCategory = new Category();
        expectedProduct = Product.builder()
                .id(1L)
                .title("test")
                .cost(new BigDecimal("10.0"))
                .build();

//        when(shopService.getAllProducts(Mockito.any())).thenReturn(Collections.singletonList(new ProductDTO(expectedProduct)));
        when(shopService.getProductByIdOrEmpty(1L)).thenReturn(new ProductDTO(expectedProduct));
        when(shopService.getAllCategories()).thenReturn(Collections.singletonList(new CategoryDTO(expectedCategory)));
    }

    @Test
    @SneakyThrows
    void getProductsReqShouldReturnProductPageWithDTOData() {
        Page page = Mockito.mock(Page.class);
        when(page.getTotalPages()).thenReturn(1);
//        when(shopService.getAllProducts(Mockito.any(), Mockito.any())).thenReturn(page);
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(view().name("products"))
                .andExpect(model().attribute("page", page))
                .andExpect(model().attribute("categories", Collections.singletonList(new CategoryDTO(expectedCategory))))
        ;
    }

    @Test
    @SneakyThrows
    void getProductsByIdReqShouldReturnProductPageWithDTOData() {
        mockMvc.perform(get("/product/form?id=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("one_product"))
                .andExpect(model().attribute("product", new ProductDTO(expectedProduct)))
                .andExpect(model().attribute("categories", Collections.singletonList(new CategoryDTO(expectedCategory))))
            ;
    }

    @Test
    @SneakyThrows
    void postProductReqShouldStoreProduct() {
        mockMvc.perform(post("/product")
                .param("title", "test")
                .param("cost", "10.0")
                .param("categoryId", "1")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product"));
        verify(shopService, times(1)).saveWithImage(Mockito.any(), Mockito.any());
    }
}
