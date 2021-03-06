package ru.darvell.gb.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.darvell.gb.spring.domain.ShopUser;

import java.util.Optional;

@Repository
public interface ShopUserRepository extends JpaRepository<ShopUser, Long> {

    Optional<ShopUser> findShopUserByLogin(String login);
}
