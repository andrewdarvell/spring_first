package ru.darvell.gb.spring.repository.product_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.product_type.ProductType;
import ru.darvell.gb.spring.domain.product_type.ProductTypeValue;

import java.util.List;

@Repository
public interface ProductTypeValueRepository extends JpaRepository<ProductTypeValue, Long> {

    List<ProductTypeValue> getAllByProductAndProductType(Product product, ProductType productType);
}
