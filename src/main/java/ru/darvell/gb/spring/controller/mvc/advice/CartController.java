package ru.darvell.gb.spring.controller.mvc.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.darvell.gb.spring.component.ShoppingCart;

@ControllerAdvice
public class CartController {
    @ModelAttribute("shoppingCart")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }
}
