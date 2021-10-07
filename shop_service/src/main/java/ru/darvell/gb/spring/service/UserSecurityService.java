package ru.darvell.gb.spring.service;

import ru.darvell.gb.spring.domain.ShopUser;

import java.util.List;
import java.util.Optional;

public interface UserSecurityService {

    Optional<ShopUser> getCurrentUser();
    List<String> getCurrentUserRoles();
}
