package ru.darvell.gb.spring.service.primary;

import ru.darvell.gb.spring.domain.dto.NewOrderDTO;
import ru.darvell.gb.spring.domain.dto.ShopOrderSimpleDTO;

public interface OrderService {

    ShopOrderSimpleDTO addOrder(NewOrderDTO dto);
}
