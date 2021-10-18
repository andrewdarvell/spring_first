package ru.darvell.gb.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.ShopOrder;
import ru.darvell.gb.spring.domain.ShopUser;
import ru.darvell.gb.spring.exception.ShopEntityNotFoundException;
import ru.darvell.gb.spring.repository.ShopOrderRepository;
import ru.darvell.gb.spring.service.ShopOrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopOrderServiceImpl implements ShopOrderService {

    private final ShopOrderRepository shopOrderRepository;

    @Override
    public List<ShopOrder> getOrdersByUser(ShopUser user) {
        return shopOrderRepository.findAllByUser(user);
    }

    @Override
    public ShopOrder getOrderById(long id) {
        return shopOrderRepository.findById(id).orElseThrow(ShopEntityNotFoundException::new);
    }

    @Override
    public ShopOrder saveOrder(ShopOrder shopOrder) {
        return shopOrderRepository.saveAndFlush(shopOrder);
    }
}
