package ru.darvell.gb.spring.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.dto.ProductDTO;
import ru.darvell.gb.spring.service.ProductService;

import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAll().stream().map(ProductDTO::new).collect(Collectors.toList()));
        model.addAttribute("product", new ProductDTO());
        return "products";
    }

    @PostMapping
    public String addProduct(@ModelAttribute ProductDTO productDTO, Model model) {
        log.info("new product : {}", productDTO);
        if (!productDTO.getTitle().isBlank() && productDTO.getCost() != null) {
            productService.addProduct(new Product(productDTO));
        }
        return "redirect:/product";
    }

}
