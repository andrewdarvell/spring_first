package ru.darvell.gb.spring.domain.dto;

import lombok.Getter;
import lombok.Setter;
import ru.darvell.gb.spring.domain.ShopOrder;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ShopOrderFullDTO extends ShopOrderSimpleDTO {

    private Set<ShopOrderItemDTO> shopOrderItems;

    public ShopOrderFullDTO(ShopOrder shopOrder) {
        super(shopOrder);
        if (shopOrder.getShopOrderItems() != null) {
            shopOrderItems = shopOrder.getShopOrderItems().stream().map(ShopOrderItemDTO::new).collect(Collectors.toSet());
        }
    }
}
