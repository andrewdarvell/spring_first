package ru.darvell.gb.spring.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AuthResponseDTO {
    private final String token;
}
