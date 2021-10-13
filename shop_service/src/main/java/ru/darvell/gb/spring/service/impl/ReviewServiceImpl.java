package ru.darvell.gb.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.Review;
import ru.darvell.gb.spring.domain.ShopUser;
import ru.darvell.gb.spring.repository.ReviewRepository;
import ru.darvell.gb.spring.service.ReviewService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviewByProduct(Product product) {
        return reviewRepository.findAllByProduct(product);
    }

    @Override
    public List<Review> getAllReviewByUser(ShopUser user) {
        return reviewRepository.findAllByUser(user);
    }

    @Override
    public Review saveOrUpdate(Review review) {
        return reviewRepository.saveAndFlush(review);
    }
}
