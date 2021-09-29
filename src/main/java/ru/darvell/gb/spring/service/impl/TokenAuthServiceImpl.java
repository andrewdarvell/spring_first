package ru.darvell.gb.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.component.TokenHandler;
import ru.darvell.gb.spring.domain.UserAuthentication;
import ru.darvell.gb.spring.domain.dto.AuthResponseDTO;
import ru.darvell.gb.spring.exception.UserNotFoundException;
import ru.darvell.gb.spring.service.TokenAuthService;
import ru.darvell.gb.spring.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenAuthServiceImpl implements TokenAuthService {

    private static final String AUTH_HEADER_NAME = "X-Auth-Token";

    private final TokenHandler tokenHandler;
    private final UserService userService;

    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest request) {
        return Optional
                .ofNullable(request.getHeader(AUTH_HEADER_NAME))
                .flatMap(tokenHandler::extractUserId)
                .flatMap(userService::findById)
                .map(UserAuthentication::new);
    }

    @Override
    public AuthResponseDTO authUser(String userName, String password) throws BadCredentialsException {
        return new AuthResponseDTO(
                tokenHandler.generateAccessToken(
                        userService.getUserByCredentials(userName, password)
                                .orElseThrow(UserNotFoundException::new)
                        , LocalDateTime.now().plusDays(10))
        );
    }
}
