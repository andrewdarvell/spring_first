package ru.darvell.gb.spring.service.soap.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.service.ProductService;
import ru.darvell.gb.spring.service.soap.ProductSoapService;
import ru.darvell.gb.spring.ws.soap.products.Product;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSoapServiceImpl implements ProductSoapService {

    private final ProductService productService;

    @Override
    public List<Product> getAllProducts() {
        return productService.getAll().stream()
                .map(this::soapCategoryFromCategory)
                .collect(Collectors.toList());
    }

    private Product soapCategoryFromCategory(ru.darvell.gb.spring.domain.Product that){
        Product product = new Product();
        product.setId(that.getId());
        product.setTitle(that.getTitle());
        product.setCost(that.getCost());
        product.setImageLink(that.getImageLink());
        if (that.getCategory() != null) {
            product.setCategoryId(that.getCategory().getId());
        }
        return product;
    }
}
