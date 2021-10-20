package ru.darvell.gb.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.OrderStatus;
import ru.darvell.gb.spring.repository.OrderStatusRepository;
import ru.darvell.gb.spring.service.OrderStatusService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    @Override
    public Optional<OrderStatus> findByTitle(String title) {
        return orderStatusRepository.findByTitle(title);
    }
}
