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

import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.getAll().stream().map(CategoryDTO::new).collect(Collectors.toList()));
        model.addAttribute("category", new CategoryDTO());
        return "categories";
    }


//    @GetMapping(value = "/{id}")
//    public String getCategory(@PathVariable(name = "id") long id, Model model) {
//        model.addAttribute("product", new CategoryDTO(categoryService.findById(id).orElse(new Category())));
//        return "one_product";
//    }

    @PostMapping
    public String addCategory(@ModelAttribute CategoryDTO categoryDTO) {
        log.info("new category : {}", categoryDTO);
        if (!categoryDTO.getTitle().isBlank()) {
            categoryService.saveOrUpdate(new Category(categoryDTO));
        }
        return "redirect:/category";
    }
}
