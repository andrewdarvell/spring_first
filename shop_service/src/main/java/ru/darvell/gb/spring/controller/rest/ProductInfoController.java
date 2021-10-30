package ru.darvell.gb.spring.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.darvell.gb.spring.domain.product_type.dto.ProductTypeValueDTO;
import ru.darvell.gb.spring.service.primary.ProductInfoService;

import java.util.List;

import static ru.darvell.gb.spring.util.ShopConstants.*;

@RestController
@RequestMapping(REST_URL_V1 + PRODUCT_INFO_URL)
@RequiredArgsConstructor
public class ProductInfoController {

    private final ProductInfoService productInfoService;

    @GetMapping(value = "/by_product/{productId}")
    public List<ProductTypeValueDTO> getAllInfoByProduct(@PathVariable(name = "productId") long productId) {
        return productInfoService.getAllInfoByProductWithEmpty(productId);
    }
}
