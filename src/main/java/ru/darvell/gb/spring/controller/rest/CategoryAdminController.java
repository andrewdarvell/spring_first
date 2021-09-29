package ru.darvell.gb.spring.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.darvell.gb.spring.domain.dto.CategoryDTO;
import ru.darvell.gb.spring.service.ShopService;

import static ru.darvell.gb.spring.util.ShopConstants.*;

@RestController
@RequestMapping(REST_URL_V1 + ADMIN_URL + CATEGORY_URL)
@RequiredArgsConstructor
@CrossOrigin
public class CategoryAdminController {

    private final ShopService shopService;

    @PostMapping()
    public CategoryDTO addCategory(@RequestBody CategoryDTO categoryDTO) {
        return shopService.addCategory(categoryDTO);
    }

    @PutMapping()
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO) {
        return shopService.updateCategory(categoryDTO);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        shopService.deleteCategory(categoryId);
    }

}
