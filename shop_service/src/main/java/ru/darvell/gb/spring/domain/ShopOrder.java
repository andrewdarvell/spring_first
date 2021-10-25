package ru.darvell.gb.spring.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "shop_order")
@Builder
@Getter
@Setter
public class ShopOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "Пользователь обязателен")
    @ToString.Exclude
    private ShopUser user;

    @Column(name = "delivery_address")
    @NotNull(message = "Адрес доставки обязателен")
    private String deliveryAddress;

    @Column(name = "delivery_address_fias_id")
    private String deliveryAddressFiasId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private Set<ShopOrderItem> shopOrderItems;


    @ManyToOne
    @JoinColumn(name = "status_id")
    @NotNull(message = "Статус обязателен")
    private OrderStatus status;

    @Column(name = "cost")
    @NotNull(message = "Стоимость заказа обязательна")
    private BigDecimal cost;

}
