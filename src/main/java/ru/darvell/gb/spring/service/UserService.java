package ru.darvell.gb.spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.darvell.gb.spring.domain.ShopUser;
import ru.darvell.gb.spring.domain.dto.ShopUserDTO;
import ru.darvell.gb.spring.domain.dto.ShopUserRegisterDTO;
import ru.darvell.gb.spring.exception.ShopException;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    List<ShopUserDTO> getAllUsers();
    void addUser(ShopUserRegisterDTO dto) throws ShopException;
    void changeUserStatus(long userId, boolean enabled);
    Optional<ShopUser> findById(long id);
    Optional<ShopUser> getUserByCredentials(String userName, String password);
}
