package ru.darvell.gb.spring.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.darvell.gb.spring.exception.ShopException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EntityValidator {

    private final Validator validator;

    public  <T> void checkShopEntity(T t) throws ShopException {
        String errorsString = validator.validate(t).stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));
        if (!errorsString.isEmpty()) {
            throw new ShopException(errorsString);
        }
    }
}
