package ru.darvell.gb.spring.domain.dto;

import lombok.Getter;
import lombok.Setter;
import ru.darvell.gb.spring.domain.ShopOrder;

@Getter
@Setter
public class ShopOrderSimpleDTO {

    private long id;
    private long userId;
    private String userName;
    private String deliveryAddress;
    private String deliveryAddressFiasId;
    private String comment;

    public ShopOrderSimpleDTO(ShopOrder shopOrder) {
        id = shopOrder.getId();
        userId = shopOrder.getUser().getId();
        userName = shopOrder.getUser().getLogin();
        deliveryAddress = shopOrder.getDeliveryAddress();
        deliveryAddressFiasId = shopOrder.getDeliveryAddressFiasId();
        comment = shopOrder.getComment();
    }
}
