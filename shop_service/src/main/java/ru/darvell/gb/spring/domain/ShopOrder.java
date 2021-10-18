package ru.darvell.gb.spring.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private Set<ShopOrderItem> shopOrderItems;



}
