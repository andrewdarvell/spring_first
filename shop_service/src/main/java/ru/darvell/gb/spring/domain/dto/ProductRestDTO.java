package ru.darvell.gb.spring.domain.dto;

import lombok.*;
import ru.darvell.gb.spring.domain.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductRestDTO extends ProductDTO {

    private String saveImageLink = null;

    public ProductRestDTO(Product product) {
        super(product);
    }

    public ProductRestDTO(ProductDTO productDTO) {
        super(productDTO);
    }

}
