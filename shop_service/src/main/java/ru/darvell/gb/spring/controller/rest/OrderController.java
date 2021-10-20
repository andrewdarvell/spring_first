package ru.darvell.gb.spring.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.darvell.gb.spring.domain.dto.NewOrderDTO;
import ru.darvell.gb.spring.domain.dto.ShopOrderFullDTO;
import ru.darvell.gb.spring.domain.dto.ShopOrderSimpleDTO;
import ru.darvell.gb.spring.service.primary.OrderService;

import java.util.List;

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

    @GetMapping
    @RequestMapping("")
    public List<ShopOrderFullDTO> getOrders(){
        return orderService.getOrders();
    }
}
