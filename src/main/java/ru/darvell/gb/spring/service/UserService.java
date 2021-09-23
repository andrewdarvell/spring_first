package ru.darvell.gb.spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.darvell.gb.spring.domain.dto.ShopUserDTO;
import ru.darvell.gb.spring.domain.dto.ShopUserRegisterDTO;
import ru.darvell.gb.spring.exception.ShopException;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<ShopUserDTO> getAllUsers();
    void addUser(ShopUserRegisterDTO dto) throws ShopException;
    void changeUserStatus(long userId, boolean enabled);
}
