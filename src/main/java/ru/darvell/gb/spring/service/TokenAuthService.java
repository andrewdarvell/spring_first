package ru.darvell.gb.spring.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import ru.darvell.gb.spring.domain.dto.AuthResponseDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface TokenAuthService {
    Optional<Authentication> getAuthentication(HttpServletRequest request);
    AuthResponseDTO authUser(String userName, String password) throws BadCredentialsException;
}
