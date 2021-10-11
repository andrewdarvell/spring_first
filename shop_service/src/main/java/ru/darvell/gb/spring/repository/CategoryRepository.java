package ru.darvell.gb.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.darvell.gb.spring.domain.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findCategoryByTitleIgnoreCase(String title);
    List<Category> findCategoriesByParentCategoryIsNull();
}
