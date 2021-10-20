package ru.darvell.gb.spring.service.primary;

import ru.darvell.gb.spring.domain.dto.NewOrderDTO;
import ru.darvell.gb.spring.domain.dto.ShopOrderFullDTO;
import ru.darvell.gb.spring.domain.dto.ShopOrderSimpleDTO;

import java.util.List;

public interface OrderService {

    ShopOrderSimpleDTO addOrder(NewOrderDTO dto);
    List<ShopOrderFullDTO> getOrders();
}
