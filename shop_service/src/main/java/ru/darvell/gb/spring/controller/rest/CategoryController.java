package ru.darvell.gb.spring.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.darvell.gb.spring.domain.dto.CategoryDTO;
import ru.darvell.gb.spring.domain.dto.CategoryWithChildsDTO;
import ru.darvell.gb.spring.service.ShopService;


import java.util.List;

import static ru.darvell.gb.spring.util.ShopConstants.CATEGORY_URL;
import static ru.darvell.gb.spring.util.ShopConstants.REST_URL_V1;

@RestController("restCategoryController")
@RequestMapping(REST_URL_V1 + CATEGORY_URL)
@RequiredArgsConstructor
public class CategoryController {

    private final ShopService shopService;

    @GetMapping("/{categoryId}")
    public CategoryDTO getCategoryById(@PathVariable Long categoryId){
        return shopService.getCategoryDTOById(categoryId);
    }

    @GetMapping("/tree")
    public List<CategoryWithChildsDTO> getAllCategoryTree(){
        return shopService.getAllCategoryTrees();
    }

    @GetMapping()
    public List<CategoryDTO> getAllCategoryFlat(){
        return shopService.getAllCategories();
    }

}
