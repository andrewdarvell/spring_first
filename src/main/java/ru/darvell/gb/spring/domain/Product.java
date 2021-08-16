package ru.darvell.gb.spring.domain;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private long id;
    private String title;
    private BigDecimal cost;
}
