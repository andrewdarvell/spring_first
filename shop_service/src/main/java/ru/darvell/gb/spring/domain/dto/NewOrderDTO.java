package ru.darvell.gb.spring.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class NewOrderDTO {

    private String deliveryAddress;
    private String deliveryAddressFiasId;
    private String comment;
    private Set<ShopOrderItemDTO> shopOrderItems = new HashSet<>();
}
