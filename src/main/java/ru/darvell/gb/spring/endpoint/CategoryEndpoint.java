package ru.darvell.gb.spring.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.darvell.gb.spring.service.soap.CategorySoapService;
import ru.darvell.gb.spring.ws.soap.categories.GetAllCategoriesFlatResponse;

@Endpoint
@RequiredArgsConstructor
@Slf4j
public class CategoryEndpoint {

    private static final String NAMESPACE_URI = "http://www.darvell.ru/spring/ws/categories";

    private final CategorySoapService categorySoapService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllCategoriesFlatRequest")
    @ResponsePayload
    public GetAllCategoriesFlatResponse getAllCategoriesFlat() {
        GetAllCategoriesFlatResponse response = new GetAllCategoriesFlatResponse();
        response.setCategories(categorySoapService.getAllCategoriesFlat());
        return response;
    }
}
