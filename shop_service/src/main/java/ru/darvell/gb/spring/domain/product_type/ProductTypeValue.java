package ru.darvell.gb.spring.domain.product_type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.darvell.gb.spring.domain.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "product_type_value")
@Getter
@Setter
@ToString
public class ProductTypeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotBlank(message = "Продукт обязателен")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_type_dict_id")
    @NotBlank(message = "Обязательно указания на словарь параметров")
    private ProductTypeDict productTypeDict;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @Column(name = "value")
    @NotBlank(message = "Значение параметра одязательно")
    private String value;


}
