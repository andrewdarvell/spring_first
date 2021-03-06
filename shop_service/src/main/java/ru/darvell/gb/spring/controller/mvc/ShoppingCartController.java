package ru.darvell.gb.spring.controller.mvc;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.darvell.gb.spring.component.ShoppingCart;
import ru.darvell.gb.spring.service.ProductService;


@Controller
@RequestMapping("/cart")
@SessionAttributes("shoppingCart")
@AllArgsConstructor
@Slf4j
public class ShoppingCartController {

    private final ProductService productService;

    @GetMapping("/list")
    public String showCart() {
        return "cart/list";
    }

    @GetMapping("/add-to-cart")
    public RedirectView addToCart(@RequestParam Long id, @ModelAttribute ShoppingCart cart) {
        productService.findById(id).ifPresent(cart::addProduct);
        log.info("add to cart");
        return new RedirectView("/product");
    }

    @GetMapping("/remove-from-cart")
    public RedirectView removeFromCart(@RequestParam Long id, @ModelAttribute ShoppingCart cart) {
        productService.findById(id).ifPresent(cart::removeProduct);
        return new RedirectView("/cart/list");
    }

}
