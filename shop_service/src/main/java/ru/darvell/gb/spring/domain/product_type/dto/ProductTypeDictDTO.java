package ru.darvell.gb.spring.domain.product_type.dto;

import lombok.*;
import ru.darvell.gb.spring.domain.product_type.ProductTypeDict;

@NoArgsConstructor
@Getter
@Setter
public class ProductTypeDictDTO {

    private Long id;
    private String title;
    private long sortOrder;
    private long dictValueTypeId;

    public ProductTypeDictDTO(ProductTypeDict dict) {
        id = dict.getId();
        title = dict.getTitle();
        sortOrder = dict.getSortOrder();
        dictValueTypeId = dict.getDictValueType().getId();
    }

}
