package ru.darvell.gb.spring.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.darvell.gb.spring.domain.product_type.dto.DictValueTypeDTO;
import ru.darvell.gb.spring.domain.product_type.dto.ProductTypeDTO;
import ru.darvell.gb.spring.domain.product_type.dto.ProductTypeDictDTO;
import ru.darvell.gb.spring.service.primary.ProductInfoService;

import java.util.List;

import static ru.darvell.gb.spring.util.ShopConstants.*;

@RequestMapping(REST_URL_V1 + ADMIN_URL + PRODUCT_INFO_URL)
@RequiredArgsConstructor
@RestController
public class ProductInfoAdminController {

    private final ProductInfoService productInfoService;


    @GetMapping(value = "/type")
    public List<ProductTypeDTO> getAllProductTypes(@RequestParam(name = "title", required = false) String title) {
        return productInfoService.getAllProductTypes(title);
    }

    @GetMapping(value = "/type/{id}")
    public ProductTypeDTO getProductTypeDataById(@PathVariable(name = "id") long id) {
        return productInfoService.getProductTypeById(id);
    }

    @PostMapping(value = "/type")
    public ProductTypeDTO updateProductType(@RequestBody ProductTypeDTO productTypeDTO) {
        return productInfoService.updateProductType(productTypeDTO);
    }

    @GetMapping(value = "/type/value/dict")
    public List<DictValueTypeDTO> getAllDistValueTypes() {
        return productInfoService.getAllDictValueTypes();
    }


    @GetMapping(value = "/type/{id}/dict")
    public List<ProductTypeDictDTO> getDictByProductType(@PathVariable(name = "id") long productTypeId) {
        return productInfoService.getDictByTypeId(productTypeId);
    }

    @PutMapping(value = "/type/{id}/dict")
    public List<ProductTypeDictDTO> updateProductTypeDict(@PathVariable(name = "id") long productTypeId,
                                                   @RequestBody List<ProductTypeDictDTO> productTypeDictDTO) {
        return productInfoService.updateProductTypeDict(productTypeId, productTypeDictDTO);
    }


}
