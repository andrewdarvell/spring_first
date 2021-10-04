package ru.darvell.gb.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.domain.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , ProductRepositoryCustomSelector{

    List<Product> findAllByCategory(Category category);
    List<Product> findAllByIdIn(List<Long> ids);
}
