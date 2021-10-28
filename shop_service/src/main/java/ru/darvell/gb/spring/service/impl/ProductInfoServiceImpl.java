package ru.darvell.gb.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.product_type.ProductType;
import ru.darvell.gb.spring.domain.product_type.ProductTypeDict;
import ru.darvell.gb.spring.domain.product_type.dto.DictValueTypeDTO;
import ru.darvell.gb.spring.domain.product_type.dto.ProductTypeDTO;
import ru.darvell.gb.spring.domain.product_type.dto.ProductTypeDictDTO;
import ru.darvell.gb.spring.domain.product_type.dto.ProductTypeValueDTO;
import ru.darvell.gb.spring.exception.ShopEntityNotFoundException;
import ru.darvell.gb.spring.service.ProductService;
import ru.darvell.gb.spring.service.primary.ProductInfoService;
import ru.darvell.gb.spring.service.product_type.DictValueService;
import ru.darvell.gb.spring.service.product_type.ProductTypeDictService;
import ru.darvell.gb.spring.service.product_type.ProductTypeService;
import ru.darvell.gb.spring.service.product_type.ProductTypeValueService;
import ru.darvell.gb.spring.util.EntityValidator;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductInfoServiceImpl implements ProductInfoService {

    private final ProductTypeValueService productTypeValueService;
    private final ProductService productService;
    private final ProductTypeService productTypeService;
    private final EntityValidator validator;
    private final DictValueService dictValueService;
    private final ProductTypeDictService productTypeDictService;

    @Override
    public List<ProductTypeValueDTO> getAllInfoValuesByProduct(long productId) {
        return getAllInfoValuesByProduct(productService.findById(productId).orElseThrow(() -> new ShopEntityNotFoundException("Продукт не найден")));
    }

    @Override
    public List<ProductTypeValueDTO> getAllInfoValuesByProduct(Product product) {
        return productTypeValueService.getAllByProduct(product).stream().map(ProductTypeValueDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductTypeDTO> getAllProductTypes(String title) {
        if (title == null) {
            return productTypeService.getAll().stream().map(ProductTypeDTO::new).collect(Collectors.toList());
        } else {
            return productTypeService.getAllFilteredByTitle(title).stream().map(ProductTypeDTO::new).collect(Collectors.toList());
        }
    }

    @Override
    public ProductTypeDTO getProductTypeById(long id) {
        return new ProductTypeDTO(productTypeService.getById(id).orElseThrow(() -> new ShopEntityNotFoundException("Тип продукта не найден")));
    }

    @Override
    public ProductTypeDTO updateProductType(ProductTypeDTO productTypeDTO) {
        ProductType productType = ProductType.builder()
                .id(productTypeDTO.getId())
                .title(productTypeDTO.getTitle())
                .build();
        validator.checkShopEntity(productTypeDTO);
        return new ProductTypeDTO(productTypeService.saveAndFlush(productType));
    }

    @Override
    public List<DictValueTypeDTO> getAllDictValueTypes() {
        return dictValueService.getAll().stream().map(DictValueTypeDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductTypeDictDTO> getDictByTypeId(long typeId) {
        return (productTypeDictService.getAllByProductType(productTypeService.getById(typeId).orElseThrow(() -> new ShopEntityNotFoundException("Тип продукта не найден"))))
                .stream().map(ProductTypeDictDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductTypeDictDTO> updateProductTypeDict(long typeId, List<ProductTypeDictDTO> productTypeDictDTO) {
        final ProductType productType = productTypeService.getById(typeId).orElseThrow(() -> new ShopEntityNotFoundException("Тип продукта не найден"));
        List<ProductTypeDict> result = productTypeDictService.saveAndFlushBulk(productTypeDictDTO.stream().map(d -> ProductTypeDict.builder()
                .id(d.getId())
                .title(d.getTitle())
                .sortOrder(d.getSortOrder())
                .productType(productType)
                .dictValueType(dictValueService.getById(d.getDictValueTypeId()).orElseThrow(() -> new ShopEntityNotFoundException("Тип параметра не найден")))
                .build()).collect(Collectors.toList())
        );
        return result.stream().map(ProductTypeDictDTO::new).collect(Collectors.toList());
    }
}
