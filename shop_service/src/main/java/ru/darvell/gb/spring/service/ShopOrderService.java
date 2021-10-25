package ru.darvell.gb.spring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.darvell.gb.spring.domain.ShopOrder;
import ru.darvell.gb.spring.domain.ShopUser;

import java.util.List;

public interface ShopOrderService {

    List<ShopOrder> getOrdersByUser(ShopUser user);
    ShopOrder getOrderById(long id);
    ShopOrder saveOrder(ShopOrder shopOrder);
    Page<ShopOrder> getOrdersByUser(ShopUser user, Pageable pageable);
}
