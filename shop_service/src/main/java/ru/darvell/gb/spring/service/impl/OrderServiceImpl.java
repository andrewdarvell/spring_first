package ru.darvell.gb.spring.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.ShopOrder;
import ru.darvell.gb.spring.domain.ShopOrderItem;
import ru.darvell.gb.spring.domain.ShopUser;
import ru.darvell.gb.spring.domain.dto.NewOrderDTO;
import ru.darvell.gb.spring.domain.dto.ShopOrderFullDTO;
import ru.darvell.gb.spring.domain.dto.ShopOrderItemDTO;
import ru.darvell.gb.spring.domain.dto.ShopOrderSimpleDTO;
import ru.darvell.gb.spring.exception.ActionNeedAuthException;
import ru.darvell.gb.spring.exception.ShopEntityNotFoundException;
import ru.darvell.gb.spring.service.ProductService;
import ru.darvell.gb.spring.service.ShopOrderItemService;
import ru.darvell.gb.spring.service.ShopOrderService;
import ru.darvell.gb.spring.service.UserSecurityService;
import ru.darvell.gb.spring.service.primary.OrderService;
import ru.darvell.gb.spring.util.EntityValidator;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final ShopOrderService shopOrderService;
    private final ShopOrderItemService shopOrderItemService;
    private final UserSecurityService userSecurityService;
    private final EntityValidator validator;
    private final ProductService productService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ShopOrderSimpleDTO addOrder(NewOrderDTO dto) {
        ShopOrder shopOrder = new ShopOrder();
        ShopUser user = userSecurityService.getCurrentUser()
                .orElseThrow(ActionNeedAuthException::new);
        shopOrder.setComment(dto.getComment());
        shopOrder.setDeliveryAddress(dto.getDeliveryAddress());
        shopOrder.setDeliveryAddressFiasId(dto.getDeliveryAddressFiasId());
        shopOrder.setComment(dto.getComment());
        shopOrder.setUser(user);
        validator.checkShopEntity(shopOrder);
        shopOrder = shopOrderService.saveOrder(shopOrder);
        addOrderItems(shopOrder, dto.getShopOrderItems());

        log.info("new order {}", shopOrder);
        return new ShopOrderSimpleDTO(shopOrder);
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
