package ru.darvell.gb.spring.controller.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.darvell.gb.spring.domain.dto.AuthResponseDTO;
import ru.darvell.gb.spring.service.TokenAuthService;
import ru.darvell.gb.spring.service.UserSecurityService;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShopUserControllerTest {

    private TokenAuthService tokenAuthService;
    private UserSecurityService userSecurityService;
    private ShopUserController shopUserController;

    @BeforeEach
    void setUp() {
        tokenAuthService = Mockito.mock(TokenAuthService.class);
        userSecurityService = Mockito.mock(UserSecurityService.class);
        shopUserController = new ShopUserController(tokenAuthService, userSecurityService);
    }

    @Test
    void authUser() {
        AuthResponseDTO expected = new AuthResponseDTO("token");
        when(tokenAuthService.authUser(Mockito.anyString(), Mockito.anyString())).thenReturn(expected);
        AuthResponseDTO actual = shopUserController.authUser("login", "password");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getCurrentUserRoles() {
        List<String> roles = List.of("ROLE");
        when(userSecurityService.getCurrentUserRoles()).thenReturn(roles);
        List<String> actual = shopUserController.getCurrentUserRoles();
        Assertions.assertEquals(roles, actual);

    }
}
