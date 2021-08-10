package ru.darvell.gb.spring.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.service.CartService;
import ru.darvell.gb.spring.service.ProductService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Service
@AllArgsConstructor
@Scope(SCOPE_PROTOTYPE)
public class CartServiceImpl implements CartService {

    private final List<Product> products = new LinkedList<>();
    private final ProductService productService;

    @Override
    public boolean addProduct(long id) {
        Optional<Product> productOptional = productService.getById(id);
        if (productOptional.isPresent()) {
            addProductToCart(productOptional.get());
            return true;
        }
        return false;
    }

    private void addProductToCart(Product p) {
        products.add(p);
    }

    @Override
    public boolean removeProduct(long id) {
        return products.removeIf(p -> p.getId() == id);
    }

    @Override
    public List<Product> getAllProductsInCart() {
        return products;
    }


}
