package ru.darvell.gb.spring.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.service.ProductService;

@Controller
@AllArgsConstructor
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("product", new Product());
        return "products";
    }

    @PostMapping
    public String addProduct(@ModelAttribute Product product, Model model) {
        log.info("new product : {}", product);
        if (!product.getTitle().isBlank() && product.getCost() != null) {
            productService.addProduct(product);
        }
        return "redirect:/product";
    }

}
