package ru.darvell.gb.spring.service;

import ru.darvell.gb.spring.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getAll();
    Optional<Category> findById(long id);
    Optional<Category> findByTitle(String title);
    Category saveOrUpdate(Category category);
}
