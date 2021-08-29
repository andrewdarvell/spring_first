package ru.darvell.gb.spring.controller;


import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.darvell.gb.spring.MVCTestConfig;
import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.dto.CategoryDTO;
import ru.darvell.gb.spring.domain.dto.ProductDTO;
import ru.darvell.gb.spring.service.CategoryService;
import ru.darvell.gb.spring.service.ProductService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CategoryControllerTest extends MVCTestConfig {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public CategoryService categoryService;

    public Category expectedCategory;

    @BeforeEach
    public void setUp() {
        expectedCategory = Category.builder()
                .id(1L)
                .title("test")
                .build();
        when(categoryService.getAll()).thenReturn(Collections.singletonList(expectedCategory));
        when(categoryService.findById(1L)).thenReturn(Optional.of(expectedCategory));
    }

    @Test
    @SneakyThrows
    void getCategoriesReqShouldReturnCategoryPageWithDTOData() {
        Category emptyExpectedCategory = new Category();
        mockMvc.perform(get("/category"))
                .andExpect(status().isOk())
                .andExpect(view().name("categories"))
                .andExpect(model().attribute("categories", Collections.singletonList(new CategoryDTO(expectedCategory))))
                .andExpect(model().attribute("category", new CategoryDTO(emptyExpectedCategory)));
    }


    @Test
    @SneakyThrows
    void postCategoryReqShouldStoreCategory() {
        mockMvc.perform(post("/category")
                .param("title", "test")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/category"));
        verify(categoryService, times(1)).saveOrUpdate(Mockito.any());
    }
}
