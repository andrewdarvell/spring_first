package ru.darvell.gb.spring.domain;

import lombok.*;
import ru.darvell.gb.spring.domain.dto.CategoryDTO;
import ru.darvell.gb.spring.domain.dto.ProductDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "category")
    List<Product> products;

    public Category(CategoryDTO that) {
        id = that.getId();
        title = that.getTitle();
    }

}
