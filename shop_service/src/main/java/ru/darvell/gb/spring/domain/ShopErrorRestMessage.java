package ru.darvell.gb.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Data
public class ShopErrorRestMessage {
    private final HttpStatus httpStatus;
    private final String message;
}