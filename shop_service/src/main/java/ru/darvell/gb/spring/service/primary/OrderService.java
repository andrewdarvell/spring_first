package ru.darvell.gb.spring.service.primary;

import org.springframework.data.domain.Page;
import ru.darvell.gb.spring.domain.dto.NewOrderDTO;
import ru.darvell.gb.spring.domain.dto.ShopOrderFullDTO;
import ru.darvell.gb.spring.domain.dto.ShopOrderSimpleDTO;
import ru.darvell.gb.spring.domain.nonpersist.FilterOrderRequest;

import java.util.List;

public interface OrderService {

    ShopOrderSimpleDTO addOrder(NewOrderDTO dto);
    List<ShopOrderFullDTO> getOrders();
    Page<ShopOrderSimpleDTO> getOrders(FilterOrderRequest filterOrderRequest);
}
