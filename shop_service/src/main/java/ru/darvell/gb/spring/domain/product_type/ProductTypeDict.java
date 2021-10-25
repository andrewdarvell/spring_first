package ru.darvell.gb.spring.domain.product_type;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product_type_dict")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeDict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Имя параметра обязятельно")
    @Column(name = "title")
    private String title;

    @Column(name = "sort_order")
    private long sortOrder;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    @NotNull(message = "Тип продукта обязателен")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "dict_value_type_id")
    @NotNull(message = "Тип значения обязателен")
    private DictValueType dictValueType;

}
