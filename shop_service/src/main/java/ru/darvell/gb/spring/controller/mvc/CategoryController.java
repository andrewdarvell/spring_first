package ru.darvell.gb.spring.controller.mvc;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.darvell.gb.spring.domain.dto.CategoryDTO;
import ru.darvell.gb.spring.exception.ShopException;
import ru.darvell.gb.spring.service.primary.ShopService;


@Controller
@AllArgsConstructor
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    private final ShopService shopService;

    @GetMapping
    public String getCategories(Model model) {
        prepareModelForForm(model, new CategoryDTO(), "");
        return "categories";
    }


    @PostMapping
    public String addCategory(@ModelAttribute CategoryDTO categoryDTO, Model model) {
        try {
            shopService.saveOrUpdateCategoryWithoutParent(categoryDTO);
        } catch (ShopException e) {
            prepareModelForForm(model, categoryDTO, e.getMessage());
            return "categories";
        }
        return "redirect:/category";
    }

    private Model prepareModelForForm(Model model, CategoryDTO categoryDTO, String errors) {
        model.addAttribute("category", categoryDTO);
        model.addAttribute("errors", errors);
        model.addAttribute("categories", shopService.getAllCategories());
        return model;
    }
}
