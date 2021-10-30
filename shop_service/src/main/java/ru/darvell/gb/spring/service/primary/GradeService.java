package ru.darvell.gb.spring.service.primary;

import ru.darvell.gb.spring.domain.dto.ReviewDTO;

import java.util.List;

public interface GradeService {

    List<ReviewDTO> getAllReviewByProduct(long productId);
    List<ReviewDTO> getAllReviewByUser(long userId);
    ReviewDTO addReview(long productId, ReviewDTO dto);
}
