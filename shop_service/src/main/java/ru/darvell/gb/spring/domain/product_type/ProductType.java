package ru.darvell.gb.spring.domain.product_type;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "product_type")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Имя типа продукта обязятельно")
    @Column(name = "title")
    private String title;


}
