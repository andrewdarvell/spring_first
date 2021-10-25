package ru.darvell.gb.spring.domain.product_type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "dict_value_type")
@Getter
@Setter
@ToString
public class DictValueType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Имя типа значения обязательно")
    @Column(name = "title")
    private String title;
}
