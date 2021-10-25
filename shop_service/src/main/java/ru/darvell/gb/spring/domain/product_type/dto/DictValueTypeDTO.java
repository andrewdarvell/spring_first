package ru.darvell.gb.spring.domain.product_type.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.darvell.gb.spring.domain.product_type.DictValueType;

@NoArgsConstructor
@Getter
@Setter
public class DictValueTypeDTO {

    private Long id;
    private String title;

    public DictValueTypeDTO(DictValueType dictValueType) {
        id = dictValueType.getId();
        title = dictValueType.getTitle();
    }
}
