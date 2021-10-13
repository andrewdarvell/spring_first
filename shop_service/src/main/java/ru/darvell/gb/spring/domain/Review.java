package ru.darvell.gb.spring.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "review")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pluses_description")
    private String pluses;

    @Column(name = "minuses_description")
    private String minuses;

    @Column(name = "rating")
    @NotNull(message = "Рейтинг обязателен")
    private Byte rating;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull(message = "Продукт обязателен")
    @ToString.Exclude
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "Пользователь обязателен")
    @ToString.Exclude
    private ShopUser user;

}
