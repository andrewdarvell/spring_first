package ru.darvell.gb.spring.service.product_type;

import ru.darvell.gb.spring.domain.product_type.DictValueType;

import java.util.Optional;

public interface DictValueService {

    Optional<DictValueType> getById(long id);
    DictValueType saveAndFlush(DictValueType dictValueType);

}
