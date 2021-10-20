package ru.darvell.gb.spring.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.darvell.gb.spring.service.primary.OrderService;

import static ru.darvell.gb.spring.util.ShopConstants.*;

@RestController
@RequestMapping(REST_URL_V1 + ADMIN_URL + ORDER_URL)
@RequiredArgsConstructor
public class OrderAdminController {

    private final OrderService orderService;

}
