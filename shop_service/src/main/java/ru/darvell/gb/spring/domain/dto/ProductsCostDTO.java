package ru.darvell.gb.spring.domain.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ProductsCostDTO {

    private Map<Long, BigDecimal> costs = new HashMap<>();

    public void addProductCost(long productId, BigDecimal cost) {
        costs.put(productId, cost);
    }

}
