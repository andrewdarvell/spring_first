package ru.darvell.gb.spring.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.darvell.gb.spring.domain.ShopOrderItem;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ShopOrderItemDTO {

    private long id;
    private long productId;
    private String productTitle;
    private BigDecimal cost;
    private long count;

    public ShopOrderItemDTO(ShopOrderItem item) {
        id = item.getId();
        productId = item.getProduct().getId();
        productTitle = item.getProduct().getTitle();
        cost = item.getCost();
        count = item.getCount();
    }
}
