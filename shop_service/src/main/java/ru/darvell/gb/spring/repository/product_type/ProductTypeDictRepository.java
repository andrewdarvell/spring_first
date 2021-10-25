package ru.darvell.gb.spring.repository.product_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.darvell.gb.spring.domain.product_type.DictValueType;
import ru.darvell.gb.spring.domain.product_type.ProductType;
import ru.darvell.gb.spring.domain.product_type.ProductTypeDict;

import java.util.List;

@Repository
public interface ProductTypeDictRepository extends JpaRepository<ProductTypeDict, Long> {

    List<ProductTypeDict> findAllByProductType(ProductType productType);
    List<ProductTypeDict> findAllByDictValueType(DictValueType dictValueType);
}
