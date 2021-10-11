package ru.darvell.gb.spring.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.repository.CategoryRepository;
import ru.darvell.gb.spring.service.CategoryService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    public CategoryRepository categoryRepository;

    public CategoryService categoryService;

    public Category expectedCategory;

    @BeforeEach
    public void setUp() {
        expectedCategory = Category.builder()
                .id(1L)
                .title("test")
                .build();
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void shouldReturnOptionalCategoryOnGetById() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(expectedCategory));
        Optional<Category> actualOptionalCategory = categoryService.findById(1L);
        Assertions.assertTrue(actualOptionalCategory.isPresent());
        Category actualCategory = actualOptionalCategory.get();
        Assertions.assertEquals(expectedCategory, actualCategory);
    }

    @Test
    void shouldReturnListOnGetAll() {
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(expectedCategory));
        List<Category> categories = categoryService.getAll();
        Assertions.assertNotNull(categories);
        Assertions.assertEquals(1, categories.size());
        Assertions.assertEquals(expectedCategory, categories.get(0));
    }

    @Test
    void shouldRunSaveOrUpdateOnDAO() {
        when(categoryRepository.saveAndFlush(Mockito.any())).thenReturn(expectedCategory);
        categoryService.saveOrUpdate(expectedCategory);
        verify(categoryRepository, times(1)).saveAndFlush(Mockito.any());
    }

}
