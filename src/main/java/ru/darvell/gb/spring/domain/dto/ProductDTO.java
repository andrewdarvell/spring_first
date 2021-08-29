package ru.darvell.gb.spring.domain.dto;

import lombok.*;
import ru.darvell.gb.spring.domain.Product;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductDTO {
    private Long id;
    private String title;
    private BigDecimal cost;
    private Long categoryId;
    private String categoryName;

    public ProductDTO(Product that) {
        id = that.getId();
        title = that.getTitle();
        cost = that.getCost();
        if (that.getCategory() != null) {
            categoryId = that.getCategory().getId();
            categoryName = that.getCategory().getTitle();
        }
    }
}
