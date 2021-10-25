package ru.darvell.gb.spring.service.primary;

import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.product_type.dto.DictValueTypeDTO;
import ru.darvell.gb.spring.domain.product_type.dto.ProductTypeDTO;
import ru.darvell.gb.spring.domain.product_type.dto.ProductTypeDictDTO;
import ru.darvell.gb.spring.domain.product_type.dto.ProductTypeValueDTO;

import java.util.List;

public interface ProductInfoService {

    List<ProductTypeValueDTO> getAllInfoValuesByProduct(long productId);
    List<ProductTypeValueDTO> getAllInfoValuesByProduct(Product product);

    List<ProductTypeDTO> getAllProductTypes(String title);
    ProductTypeDTO getProductTypeById(long id);
    ProductTypeDTO updateProductType(ProductTypeDTO productTypeDTO);

    List<DictValueTypeDTO> getAllDictValueTypes();

    List<ProductTypeDictDTO> getDictByTypeId(long typeId);
    List<ProductTypeDictDTO> updateProductTypeDict(long typeId, List<ProductTypeDictDTO> productTypeDictDTO);

}
