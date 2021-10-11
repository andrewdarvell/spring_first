package ru.darvell.gb.spring.service.impl;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.Role;
import ru.darvell.gb.spring.domain.ShopUser;
import ru.darvell.gb.spring.domain.UserAuthentication;
import ru.darvell.gb.spring.service.UserSecurityService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {

    public Optional<ShopUser> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        return Optional.ofNullable(((UserAuthentication) authentication).getUser());
    }

    public List<String> getCurrentUserRoles() {
        return getCurrentUser()
                .map(shopUser -> shopUser.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toList())
                )
                .orElse(Collections.emptyList());

    }
}
