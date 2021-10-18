package ru.darvell.gb.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.darvell.gb.spring.domain.ShopOrder;
import ru.darvell.gb.spring.domain.ShopUser;

import java.util.List;

@Repository
public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long> {

    List<ShopOrder> findAllByUser(ShopUser user);
}
