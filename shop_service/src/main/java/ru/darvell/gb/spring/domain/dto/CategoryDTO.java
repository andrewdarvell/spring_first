package ru.darvell.gb.spring.domain.dto;

import lombok.*;
import ru.darvell.gb.spring.domain.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CategoryDTO {

    private Long id;
    private String title;
    private Long parentCategoryId;

    public CategoryDTO(Category that) {
        id = that.getId();
        title = that.getTitle();
        if (that.getParentCategory() != null) {
            parentCategoryId = that.getParentCategory().getId();
        }
    }
}
