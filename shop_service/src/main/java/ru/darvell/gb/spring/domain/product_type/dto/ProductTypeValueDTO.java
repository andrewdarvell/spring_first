package ru.darvell.gb.spring.domain.product_type.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.darvell.gb.spring.domain.product_type.ProductTypeDict;
import ru.darvell.gb.spring.domain.product_type.ProductTypeValue;

@Getter
@Setter
@NoArgsConstructor
public class ProductTypeValueDTO {

    private Long id;
    private Long dictId;
    private Long typeId;
    private String typeTitle;
    private String title;
    private String value;
    private Long sortOrder;

    public ProductTypeValueDTO(ProductTypeValue that) {
        id = that.getId();
        dictId = that.getProductTypeDict().getId();
        typeId = that.getProductTypeDict().getDictValueType().getId();
        typeTitle = that.getProductTypeDict().getDictValueType().getTitle();
        title = that.getProductTypeDict().getTitle();
        value = that.getValue();
        sortOrder = that.getProductTypeDict().getSortOrder();
    }

    public ProductTypeValueDTO(ProductTypeDict dict) {
        id = -1L;
        dictId = dict.getId();
        typeId = dict.getDictValueType().getId();
        typeTitle = dict.getDictValueType().getTitle();
        title = dict.getTitle();
        value = null;
        sortOrder = dict.getSortOrder();
    }
}
