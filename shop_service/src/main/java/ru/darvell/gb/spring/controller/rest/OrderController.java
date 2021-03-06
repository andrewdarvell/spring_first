package ru.darvell.gb.spring.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.darvell.gb.spring.domain.dto.NewOrderDTO;
import ru.darvell.gb.spring.domain.dto.ShopOrderSimpleDTO;
import ru.darvell.gb.spring.domain.nonpersist.FilterOrderRequest;
import ru.darvell.gb.spring.service.primary.OrderService;


import static ru.darvell.gb.spring.util.ShopConstants.*;

@RestController
@RequestMapping(REST_URL_V1 + ORDER_URL)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ShopOrderSimpleDTO addOrder(@RequestBody NewOrderDTO dto){
        return orderService.addOrder(dto);
    }

    @PostMapping("/by_current_user")
    public Page<ShopOrderSimpleDTO> getOrders(@RequestBody FilterOrderRequest filterOrderRequest){
        return orderService.getOrders(filterOrderRequest);
    }
}
