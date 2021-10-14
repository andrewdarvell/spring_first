package ru.darvell.gb.spring.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.darvell.gb.spring.domain.nonpersist.FilterProductRequest;
import ru.darvell.gb.spring.domain.dto.ProductRestDTO;
import ru.darvell.gb.spring.domain.dto.ProductsCostDTO;
import ru.darvell.gb.spring.service.primary.ShopService;

import java.util.List;

import static ru.darvell.gb.spring.util.ShopConstants.PRODUCT_URL;
import static ru.darvell.gb.spring.util.ShopConstants.REST_URL_V1;

@RestController("restProductController")
@RequestMapping(REST_URL_V1 + PRODUCT_URL)
@RequiredArgsConstructor
public class ProductController {

    private final ShopService shopService;

    @GetMapping("/{productId}")
    public ProductRestDTO getProduct(@PathVariable(name = "productId") Long productId) {
        return shopService.getProductByIdForRest(productId);
    }

    @PostMapping("/list")
    public Page<ProductRestDTO> getProducts(@RequestBody FilterProductRequest filterProductRequest) {
        return shopService.getAllProductsPageable(filterProductRequest);
    }

    @PostMapping("/costs")
    public ProductsCostDTO getProductsCost(@RequestBody List<Long> productIds) {
        return shopService.getProductsCost(productIds);
    }

}
