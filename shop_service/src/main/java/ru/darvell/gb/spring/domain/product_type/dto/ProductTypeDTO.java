package ru.darvell.gb.spring.domain.product_type.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.darvell.gb.spring.domain.product_type.ProductType;

@NoArgsConstructor
@Getter
@Setter
public class ProductTypeDTO {

    private Long id;
    private String title;

    public ProductTypeDTO(ProductType productType) {
        id = productType.getId();
        title = productType.getTitle();
    }
}
