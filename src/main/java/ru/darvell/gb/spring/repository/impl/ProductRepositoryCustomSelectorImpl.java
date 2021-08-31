package ru.darvell.gb.spring.repository.impl;

import lombok.extern.slf4j.Slf4j;
import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.repository.ProductRepositoryCustomSelector;
import ru.darvell.gb.spring.service.ShopConstants;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.darvell.gb.spring.service.ShopConstants.*;

@Slf4j
public class ProductRepositoryCustomSelectorImpl implements ProductRepositoryCustomSelector {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getAllProductsFiltered(Map<String, String> filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        Path<BigDecimal> costPath = productRoot.get("cost");
        Path<String> titlePath = productRoot.get("title");
        Path<Category> categoryPath = productRoot.get("category");


        BigDecimal minCost = getDecimal(filters, KEY_MIN_COST_FILTER);
        if (minCost != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(costPath, minCost));
        }

        BigDecimal maxCost = getDecimal(filters, KEY_MAX_COST_FILTER);
        if (maxCost != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(costPath, maxCost));
        }

        String title = filters.get(KEY_TITLE_FILTER);
        if (title != null && !title.isBlank()) {
            predicates.add(criteriaBuilder.like(titlePath, "%" + title + "%"));
        }

        Long categoryId = getLong(filters, KEY_CATEGORY_ID);
        if (categoryId != null) {
            predicates.add(criteriaBuilder.equal(categoryPath, categoryId));
        }

        query.select(productRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query).getResultList();
    }

    private BigDecimal getDecimal(Map<String, String> set, String key){
        String rawVal = set.get(key);
        if (rawVal != null) {
            try {
                return new BigDecimal(rawVal);
            } catch (NumberFormatException e) {
                log.debug("Cannot cast string to decimal", e);
            }
        }
        return null;
    }

    private Long getLong(Map<String, String> set, String key){
        String rawVal = set.get(key);
        if (rawVal != null) {
            try {
                return Long.parseLong(rawVal);
            } catch (NumberFormatException e) {
                log.debug("Cannot cast string to long", e);
            }
        }
        return null;
    }

}
