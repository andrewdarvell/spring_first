package ru.darvell.gb.spring.domain;

import lombok.*;
import ru.darvell.gb.spring.domain.dto.CategoryDTO;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Product> products;

    @Column(name="parent_category_id")
    private Long parentId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_category_id")
    @ToString.Exclude
    private Set<Category> childCategories;

    public Category(CategoryDTO that) {
        id = that.getId();
        title = that.getTitle();
    }

}
