package ru.darvell.gb.spring.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.*;
import ru.darvell.gb.spring.domain.dto.*;
import ru.darvell.gb.spring.domain.nonpersist.FilterOrderRequest;
import ru.darvell.gb.spring.domain.nonpersist.FilterProductRequest;
import ru.darvell.gb.spring.exception.ActionNeedAuthException;
import ru.darvell.gb.spring.exception.ShopEntityNotFoundException;
import ru.darvell.gb.spring.service.*;
import ru.darvell.gb.spring.service.primary.OrderService;
import ru.darvell.gb.spring.util.EntityValidator;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    public static final String FIRST_STATUS_NAME = "Принят";

    private final ShopOrderService shopOrderService;
    private final ShopOrderItemService shopOrderItemService;
    private final UserSecurityService userSecurityService;
    private final EntityValidator validator;
    private final ProductService productService;
    private final OrderStatusService orderStatusService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ShopOrderSimpleDTO addOrder(NewOrderDTO dto) {
        ShopOrder shopOrder = new ShopOrder();
        ShopUser user = userSecurityService.getCurrentUser()
                .orElseThrow(ActionNeedAuthException::new);
        OrderStatus status = orderStatusService.findByTitle(FIRST_STATUS_NAME).orElseThrow(() -> new ShopEntityNotFoundException("Не найден начальный статус"));
        shopOrder.setComment(dto.getComment());
        shopOrder.setDeliveryAddress(dto.getDeliveryAddress());
        shopOrder.setDeliveryAddressFiasId(dto.getDeliveryAddressFiasId());
        shopOrder.setComment(dto.getComment());
        shopOrder.setUser(user);
        shopOrder.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        shopOrder.setStatus(status);
        shopOrder.setCost(getOrderCost(dto.getShopOrderItems()));

        log.info("new order {}", shopOrder);

        validator.checkShopEntity(shopOrder);
        shopOrder = shopOrderService.saveOrder(shopOrder);

        addOrderItems(shopOrder, dto.getShopOrderItems());
        return new ShopOrderSimpleDTO(shopOrder);
    }

    private BigDecimal getOrderCost(Set<ShopOrderItemDTO> itemsDTO) {
        return itemsDTO.stream().map(i -> {
            Product product = productService.findById(i.getProductId()).orElse(new Product());
            return product.getCostZeroIfNull().multiply(BigDecimal.valueOf(i.getCount()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<ShopOrderFullDTO> getOrders() {
        ShopUser user = userSecurityService.getCurrentUser()
                .orElseThrow(ActionNeedAuthException::new);
        return shopOrderService.getOrdersByUser(user).stream().map(ShopOrderFullDTO::new).collect(Collectors.toList());
    }

    @Override
    public Page<ShopOrderSimpleDTO> getOrders(FilterOrderRequest filterOrderRequest) {
        ShopUser user = userSecurityService.getCurrentUser()
                .orElseThrow(ActionNeedAuthException::new);
        Pageable pageable = generatePageable(filterOrderRequest);
        Page<ShopOrder> shopOrders = shopOrderService.getOrdersByUser(user, pageable);

        return new PageImpl<>(shopOrders.stream().map(ShopOrderSimpleDTO::new).collect(Collectors.toList()), pageable, shopOrders.getTotalElements());
    }

    private Pageable generatePageable(FilterOrderRequest filterOrderRequest) {
        return PageRequest.of(filterOrderRequest.getCurrPage(), filterOrderRequest.getPageSize()
                , Sort.by(filterOrderRequest.getSortDirection(), filterOrderRequest.getSortField()));
    }

    private void addOrderItems(final ShopOrder shopOrder, Set<ShopOrderItemDTO> itemsDTO) {
        itemsDTO.forEach(i -> productService.findById(i.getProductId())
                .ifPresent(product -> {
                            ShopOrderItem item = new ShopOrderItem();
                            item.setProduct(product);
                            item.setCount(i.getCount());
                            item.setCost(product.getCost());
                            item.setOrder(shopOrder);
                            validator.checkShopEntity(item);
                            shopOrderItemService.addItem(item);
                        }
                ));
    }
}
