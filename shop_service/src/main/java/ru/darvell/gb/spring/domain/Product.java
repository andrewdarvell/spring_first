package ru.darvell.gb.spring.domain;

import lombok.*;
import ru.darvell.gb.spring.domain.dto.ProductDTO;
import ru.darvell.gb.spring.domain.product_type.ProductType;
import ru.darvell.gb.spring.domain.product_type.ProductTypeValue;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

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
    private Long id;

    @NotBlank(message = "Имя продукта обязательно")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Цена продукта обязательна")
    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "image_link")
    private String imageLink;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "Категория обязательна")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private Set<ProductTypeValue> productTypeValues;

    public Product(ProductDTO that) {
        id = that.getId()== null ? -1 : that.getId();
        title = that.getTitle();
        cost = that.getCost();
        imageLink = that.getImageLink();
    }

    public BigDecimal getCostZeroIfNull(){
        return cost == null ? BigDecimal.ZERO : cost;
    }
}
