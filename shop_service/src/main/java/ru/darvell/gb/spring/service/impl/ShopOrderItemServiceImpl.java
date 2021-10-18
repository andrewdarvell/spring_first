package ru.darvell.gb.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.ShopOrderItem;
import ru.darvell.gb.spring.repository.ShopOrderItemRepository;
import ru.darvell.gb.spring.service.ShopOrderItemService;

@Service
@RequiredArgsConstructor
public class ShopOrderItemServiceImpl implements ShopOrderItemService {
    private final ShopOrderItemRepository shopOrderItemRepository;

    @Override
    public ShopOrderItem addItem(ShopOrderItem item) {
        return shopOrderItemRepository.saveAndFlush(item);
    }
}
