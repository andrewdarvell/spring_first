package ru.darvell.gb.spring.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.darvell.gb.spring.domain.Category;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.dto.CategoryDTO;
import ru.darvell.gb.spring.domain.dto.ProductDTO;
import ru.darvell.gb.spring.service.CategoryService;
import ru.darvell.gb.spring.service.ProductService;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public String getProducts(Model model, @RequestParam(name = "category", required = false) String categoryTitle) {
        List<Product> productList = new LinkedList<>();
        log.info(categoryTitle);
        if (categoryTitle != null) {
            Optional<Category> categoryOptional = categoryService.findByTitle(categoryTitle);
            if (categoryOptional.isPresent()) {
                productList = productService.getAllByCategory(categoryOptional.get());
            }
        } else {
            productList = productService.getAll();
        }

        model.addAttribute("products", productList.stream().map(ProductDTO::new).collect(Collectors.toList()));
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("categories", categoryService.getAll().stream().map(CategoryDTO::new).collect(Collectors.toList()));
        return "products";
    }


    @GetMapping(value = "/{id}")
    public String getProduct(@PathVariable(name = "id") long id, Model model) {
        model.addAttribute("product", new ProductDTO(productService.findById(id).orElse(new Product())));
        return "one_product";
    }

    @PostMapping
    public String addProduct(@ModelAttribute ProductDTO productDTO) {
        log.info("new product : {}", productDTO);
        if (!productDTO.getTitle().isBlank() && productDTO.getCost() != null) {
            Product product = new Product(productDTO);
            categoryService.findById(productDTO.getCategoryId()).ifPresent(product::setCategory);
            productService.saveOrUpdate(product);
        }
        return "redirect:/product";
    }

}
