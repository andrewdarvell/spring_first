package ru.darvell.gb.spring.domain.dto;

import lombok.*;
import ru.darvell.gb.spring.domain.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CategoryDTO {

    private long id;
    private String title;

    public CategoryDTO(Category that) {
        id = that.getId();
        title = that.getTitle();
    }
}
