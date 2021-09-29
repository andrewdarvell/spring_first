package ru.darvell.gb.spring.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.darvell.gb.spring.domain.dto.AuthResponseDTO;
import ru.darvell.gb.spring.service.TokenAuthService;

import static ru.darvell.gb.spring.util.ShopConstants.*;

@CrossOrigin
@RestController()
@RequestMapping(REST_URL_V1 + USER_URL)
@RequiredArgsConstructor
public class ShopUserController {

    private final TokenAuthService tokenAuthService;

    @PostMapping("/auth")
    public AuthResponseDTO authUser(@RequestParam(name = "login") String login,
                                    @RequestParam(name = "password") String password) {
        return tokenAuthService.authUser(login, password);
    }
}
