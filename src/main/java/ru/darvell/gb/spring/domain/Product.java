package ru.darvell.gb.spring.domain;

import lombok.*;
import ru.darvell.gb.spring.domain.dto.ProductDTO;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "product")
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(ProductDTO that) {
        id = that.getId();
        title = that.getTitle();
        cost = that.getCost();
    }
}
