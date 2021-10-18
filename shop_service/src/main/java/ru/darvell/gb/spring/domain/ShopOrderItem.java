package ru.darvell.gb.spring.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "shop_order_order_item")
@Builder
@Getter
@Setter
public class ShopOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull(message = "Продукт обязателен")
    @ToString.Exclude
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @NotNull(message = "Заказ обязателен")
    @ToString.Exclude
    private ShopOrder order;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "count")
    private Long count;
}
