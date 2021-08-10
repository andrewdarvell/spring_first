package ru.darvell.gb.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.darvell.gb.spring.configuration.AppConfig;
import ru.darvell.gb.spring.service.CartService;
import ru.darvell.gb.spring.service.ProductService;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            ProductService productService = context.getBean("productServiceImpl", ProductService.class);
            CartService cartService = context.getBean("cartServiceImpl", CartService.class);

            log.info(String.valueOf(productService.getAll().size()));
            productService.getAll().forEach(p -> log.info(p.toString()));

            cartService.addProduct(1);
            log.info("Products in cart");
            cartService.getAllProductsInCart().forEach(p -> log.info(p.toString()));
        }
    }
}
