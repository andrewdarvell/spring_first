package ru.darvell.gb.spring.domain.product_type;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "product_type_dict")
@Getter
@Setter
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
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "dict_value_type_id")
    private DictValueType dictValueType;

}
