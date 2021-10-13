package ru.darvell.gb.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.Review;
import ru.darvell.gb.spring.domain.ShopUser;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByUser(ShopUser user);
    List<Review> findAllByProduct(Product product);
}
