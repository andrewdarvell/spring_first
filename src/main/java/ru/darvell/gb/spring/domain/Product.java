package ru.darvell.gb.spring.domain;

import lombok.*;
import ru.darvell.gb.spring.domain.dto.ProductDTO;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {
    private long id;
    private String title;
    private BigDecimal cost;

    public Product(ProductDTO that) {
        id = that.getId();
        title = that.getTitle();
        cost = that.getCost();
    }
}
