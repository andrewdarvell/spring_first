package ru.darvell.gb.spring.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.darvell.gb.spring.domain.dto.ReviewDTO;
import ru.darvell.gb.spring.service.primary.GradeService;

import java.util.List;

import static ru.darvell.gb.spring.util.ShopConstants.*;

@RestController
@RequestMapping(REST_URL_V1 + GRADE_URL)
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @GetMapping("/review/by_product/{productId}")
    public List<ReviewDTO> getAllReviewByProduct(@PathVariable(name = "productId") Long productId){
        return gradeService.getAllReviewByProduct(productId);
    }

    @GetMapping("/review/by_user/{userId}")
    public List<ReviewDTO> getAllReviewByUser(@PathVariable(name = "userId") Long userId){
        return gradeService.getAllReviewByUser(userId);
    }

    @PostMapping("/review")
    public ReviewDTO addReview(@RequestBody ReviewDTO reviewDTO){
        return gradeService.addReview(reviewDTO);
    }
}
