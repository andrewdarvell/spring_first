package ru.darvell.gb.spring.service.soap;

import ru.darvell.gb.spring.ws.soap.categories.Category;

import java.util.List;

public interface CategorySoapService {

    List<Category> getAllCategoriesFlat();
}
