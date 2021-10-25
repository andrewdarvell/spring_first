package ru.darvell.gb.spring.service;

import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.OrderStatus;

import java.util.Optional;

@Service
public interface OrderStatusService {

    Optional<OrderStatus> findByTitle(String title);
}
