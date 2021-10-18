package ru.darvell.gb.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.darvell.gb.spring.domain.ShopOrderItem;

@Repository
public interface ShopOrderItemRepository extends JpaRepository<ShopOrderItem, Long> {


}
