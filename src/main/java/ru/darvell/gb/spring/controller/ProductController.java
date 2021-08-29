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
import ru.darvell.gb.spring.exception.ShopProductException;
import ru.darvell.gb.spring.service.CategoryService;
import ru.darvell.gb.spring.service.ProductService;
import ru.darvell.gb.spring.service.ShopProductService;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ShopProductService shopProductService;

    @GetMapping
    public String getProducts(Model model, @RequestParam(name = "category", required = false) String categoryTitle,
                              @RequestParam(name = "minCost", required = false) BigDecimal minCost,
                              @RequestParam(name = "maxCost", required = false) BigDecimal maxCost,
                              @RequestParam(name = "title", required = false) String title
                              ) {

        if (categoryTitle != null) {
            model.addAttribute("products", shopProductService.getAllProductsFilteredByCategoryTitle(categoryTitle));
        } else if (title != null || minCost!=null || maxCost != null) {
            model.addAttribute("products", shopProductService.getAllProductsFilterByCostAndTitle(minCost, maxCost, title));
        } else {
            model.addAttribute("products", shopProductService.getAllProducts());
        }
        model.addAttribute("filters", String.format("категория=%s, мнимальная цена=%s, максимальная цена=%s, название=%s",
                categoryTitle, maxCost, maxCost, title));
        model.addAttribute("categories", shopProductService.getAllCategories());
        return "products";
    }


    @GetMapping(value = "/form")
    public String getProduct(@RequestParam(name = "id", required = false) Long id, Model model) {
        prepareModelForForm(model,shopProductService.getProductByIdOrEmpty(id),"");
        return "one_product";
    }

    @PostMapping
    public String saveOrUpdateProduct(@ModelAttribute ProductDTO productDTO, Model model) {
        try {
            shopProductService.saveOrUpdateProduct(productDTO);
        } catch (ShopProductException e) {
            prepareModelForForm(model, productDTO, e.getMessage());
            return "one_product";
        }
        return "redirect:/product";
    }

    private Model prepareModelForForm(Model model, ProductDTO productDTO, String errors) {
        model.addAttribute("product", productDTO);
        model.addAttribute("errors", errors);
        model.addAttribute("categories",  shopProductService.getAllCategories());
        return model;
    }

}
