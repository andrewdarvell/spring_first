package ru.darvell.gb.spring.repository.impl;

import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.repository.ProductRepositoryCustomSelector;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryCustomSelectorImpl implements ProductRepositoryCustomSelector {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getAllProductsFilterByCostAndTitle(BigDecimal minCost, BigDecimal maxCost, String title) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        Path<BigDecimal> costPath = productRoot.get("cost");
        Path<String> titlePath = productRoot.get("title");

        if (minCost != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(costPath, minCost));
        }
        if (maxCost != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(costPath, maxCost));
        }

        if (title != null && !title.isBlank()) {
            predicates.add(criteriaBuilder.like(titlePath, "%" + title + "%"));
        }

        query.select(productRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query).getResultList();
    }
}
