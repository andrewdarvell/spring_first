package ru.darvell.gb.spring.service.product_type.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.product_type.DictValueType;
import ru.darvell.gb.spring.repository.product_type.DictValueTypeRepository;
import ru.darvell.gb.spring.service.product_type.DictValueService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DictValueServiceImpl implements DictValueService {

    private final DictValueTypeRepository dictValueTypeRepository;

    @Override
    public Optional<DictValueType> getById(long id) {
        return dictValueTypeRepository.findById(id);
    }

    @Override
    public List<DictValueType> getAll() {
        return dictValueTypeRepository.findAll();
    }

    @Override
    public DictValueType saveAndFlush(DictValueType dictValueType) {
        return dictValueTypeRepository.saveAndFlush(dictValueType);
    }
}
