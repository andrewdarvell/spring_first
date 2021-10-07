package ru.darvell.gb.spring.service.soap.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.service.CategoryService;
import ru.darvell.gb.spring.service.soap.CategorySoapService;
import ru.darvell.gb.spring.ws.soap.categories.Category;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategorySoapServiceImpl implements CategorySoapService {

    private final CategoryService categoryService;


    @Override
    public List<Category> getAllCategoriesFlat() {
        return categoryService.getAll().stream()
                .map(this::soapCategoryFromCategory)
                .collect(Collectors.toList());

    }

    private Category soapCategoryFromCategory(ru.darvell.gb.spring.domain.Category that){
        Category category = new Category();
        category.setId(that.getId());
        category.setTitle(that.getTitle());
        if (that.getParentCategory() != null) {
            category.setParentCategoryId(that.getParentCategory().getId());
        }
        return category;
    }
}
