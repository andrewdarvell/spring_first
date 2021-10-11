package ru.darvell.gb.spring.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.darvell.gb.spring.service.soap.ProductSoapService;
import ru.darvell.gb.spring.ws.soap.products.GetAllProductsResponse;

@Endpoint
@RequiredArgsConstructor
@Slf4j
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://www.darvell.ru/spring/ws/products";

    private final ProductSoapService productSoapService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllCategoriesFlat() {
        GetAllProductsResponse response = new GetAllProductsResponse();
        response.setProducts(productSoapService.getAllProducts());
        return response;
    }
}
