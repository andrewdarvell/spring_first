package ru.darvell.gb.spring.service;

import ru.darvell.gb.spring.domain.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findRoleByName(String name);
}
