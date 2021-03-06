package ru.darvell.gb.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.Role;
import ru.darvell.gb.spring.domain.ShopUser;
import ru.darvell.gb.spring.domain.dto.ShopUserDTO;
import ru.darvell.gb.spring.domain.dto.ShopUserRegisterDTO;
import ru.darvell.gb.spring.exception.ShopException;
import ru.darvell.gb.spring.repository.ShopUserRepository;
import ru.darvell.gb.spring.service.RoleService;
import ru.darvell.gb.spring.service.UserService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ShopUserRepository shopUserRepository;
    private final RoleService roleService;


    private Optional<ShopUser> findByUsername(String login) {
        return shopUserRepository.findShopUserByLogin(login);
    }

    public Optional<ShopUser> getUserByCredentials(String userName, String password) {
        return shopUserRepository
                .findShopUserByLogin(userName)
                .flatMap(user -> checkPassword(user, password));
    }

    private Optional<ShopUser> checkPassword(ShopUser user, String password) {
        return new BCryptPasswordEncoder().matches(password, user.getPassword()) ? Optional.of(user) : Optional.empty();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return findByUsername(login)
                .filter(ShopUser::isEnabled)
                .map(su -> new User(su.getLogin(), su.getPassword(), mapRolesToAuthorities(su.getRoles())))
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", login)));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public List<ShopUserDTO> getAllUsers() {
        return shopUserRepository.findAll().stream().map(ShopUserDTO::new).collect(Collectors.toList());
    }

    @Override
    public void addUser(ShopUserRegisterDTO dto) throws ShopException {
        ShopUser shopUser = ShopUser.builder()
                .login(dto.getLogin())
                .email(dto.getEmail())
                .password(new BCryptPasswordEncoder().encode(dto.getPassword()))
                .enabled(true)
                .roles(List.of(roleService.findRoleByName("ROLE_USER").orElseThrow(()-> new ShopException("???????????????? ???? ??????????????"))))
                .build();
        shopUserRepository.saveAndFlush(shopUser);

    }

    @Override
    public void changeUserStatus(long userId, boolean enabled) throws ShopException {
        ShopUser shopUser = shopUserRepository.findById(userId).orElseThrow(() -> new ShopException("???????????????????????? ???? ????????????"));
        shopUser.setEnabled(enabled);
        shopUserRepository.saveAndFlush(shopUser);
    }

    @Override
    public Optional<ShopUser> findById(long id) {
        return shopUserRepository.findById(id);
    }
}
