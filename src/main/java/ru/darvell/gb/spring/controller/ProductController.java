package ru.darvell.gb.spring.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.darvell.gb.spring.domain.dto.ProductDTO;
import ru.darvell.gb.spring.exception.ShopException;
import ru.darvell.gb.spring.service.ShopService;

import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/product")
@Slf4j
public class ProductController {


    private final ShopService shopService;

    @GetMapping
    public String getProducts(Model model, @RequestParam Map<String, String> allRequestParams) {
        log.info("request params {}", allRequestParams);
        model.addAttribute("products", shopService.getAllProducts(allRequestParams));
        model.addAttribute("filters", "ffff");
        model.addAttribute("categories", shopService.getAllCategories());
        return "products";
    }


    @GetMapping(value = "/form")
    public String getProduct(@RequestParam(name = "id", required = false) Long id, Model model) {
        prepareModelForForm(model, shopService.getProductByIdOrEmpty(id), "");
        return "one_product";
    }

    @PostMapping
    public String saveOrUpdateProduct(@ModelAttribute ProductDTO productDTO,
                                      @RequestParam(value = "image", required = false) MultipartFile image,
                                      Model model) {
        try {
            shopService.saveWithImage(productDTO, image);
        } catch (ShopException e) {
            prepareModelForForm(model, productDTO, e.getMessage());
            return "one_product";
        }
        return "redirect:/product";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam(name = "id") Long productId) {
        shopService.deleteProductByID(productId);
        return "redirect:/product";
    }

    private Model prepareModelForForm(Model model, ProductDTO productDTO, String errors) {
        model.addAttribute("product", productDTO);
        model.addAttribute("errors", errors);
        model.addAttribute("categories", shopService.getAllCategories());
        return model;
    }

}
