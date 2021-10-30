package ru.darvell.gb.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darvell.gb.spring.domain.Product;
import ru.darvell.gb.spring.domain.Review;
import ru.darvell.gb.spring.domain.ShopUser;
import ru.darvell.gb.spring.domain.dto.ReviewDTO;
import ru.darvell.gb.spring.exception.ActionNeedAuthException;
import ru.darvell.gb.spring.exception.ShopEntityNotFoundException;
import ru.darvell.gb.spring.service.*;
import ru.darvell.gb.spring.service.primary.GradeService;
import ru.darvell.gb.spring.util.EntityValidator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    public static final byte MIN_RATING_COUNT = 0;
    public static final byte MAX_RATING_COUNT = 4;

    private final EntityValidator validator;
    private final ReviewService reviewService;
    private final ProductService productService;
    private final UserSecurityService userSecurityService;
    private final UserService userService;


    @Override
    public List<ReviewDTO> getAllReviewByProduct(long productId) {
        return productService.findById(productId)
                .map(p ->
                        reviewService.getAllReviewByProduct(p).stream()
                                .map(ReviewDTO::new)
                                .collect(Collectors.toList())
                )
                .orElse(Collections.emptyList());
    }

    @Override
    public List<ReviewDTO> getAllReviewByUser(long userId) {
        return userService.findById(userId)
                .map(u ->
                        reviewService.getAllReviewByUser(u).stream()
                                .map(ReviewDTO::new)
                                .collect(Collectors.toList())
                )
                .orElse(Collections.emptyList());
    }

    @Override
    public ReviewDTO addReview(long productId, ReviewDTO dto) {

        ShopUser user = userSecurityService.getCurrentUser().orElseThrow(ActionNeedAuthException::new);
        Product product = productService.findById(productId).orElseThrow(ShopEntityNotFoundException::new);
        byte actualRating = dto.getRating();
        if (actualRating < MIN_RATING_COUNT || actualRating > MAX_RATING_COUNT) {
            actualRating = MAX_RATING_COUNT;
        }
        Review review = Review.builder()
                .minuses(dto.getMinuses())
                .pluses(dto.getPluses())
                .rating(actualRating)
                .user(user)
                .product(product)
                .build();
        validator.checkShopEntity(review);
        return new ReviewDTO(reviewService.saveOrUpdate(review));
    }
}
