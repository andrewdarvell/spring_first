package ru.darvell.gb.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.repository.CategoryRepository;
import ru.darvell.gb.spring.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findCategoryByTitleIgnoreCase(title).stream().findFirst();
    }

    @Override
    public Category saveOrUpdate(Category category) {
        return categoryRepository.saveAndFlush(category);
    }
}
