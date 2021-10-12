package ru.darvell.gb.spring.service;

import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.Review;
import ru.darvell.gb.spring.domain.ShopUser;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviewByProduct(Product product);
    List<Review> getAllReviewByUser(ShopUser user);
    Review saveOrUpdate(Review review);

}
