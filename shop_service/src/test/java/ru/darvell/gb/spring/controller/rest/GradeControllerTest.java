package ru.darvell.gb.spring.controller.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.darvell.gb.spring.domain.dto.ReviewDTO;
import ru.darvell.gb.spring.service.primary.GradeService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GradeControllerTest {

    private GradeService gradeService;
    private GradeController gradeController;

    @BeforeEach
    void setUp() {
        gradeService = Mockito.mock(GradeService.class);
        gradeController = new GradeController(gradeService);
    }

    @Test
    void getAllReviewByProduct() {
        when(gradeService.getAllReviewByProduct(Mockito.anyLong())).thenReturn(Collections.emptyList());
        List<ReviewDTO> actual = gradeController.getAllReviewByProduct(1L);
        Assertions.assertNotNull(actual);
    }

    @Test
    void getAllReviewByUser() {
        when(gradeService.getAllReviewByUser(Mockito.anyLong())).thenReturn(Collections.emptyList());
        List<ReviewDTO> actual = gradeController.getAllReviewByUser(1L);
        Assertions.assertNotNull(actual);
    }

    @Test
    void addReview() {
        ReviewDTO reviewDTO = Mockito.mock(ReviewDTO.class);
        when(gradeService.addReview(reviewDTO)).thenReturn(reviewDTO);
        ReviewDTO actual = gradeController.addReview(reviewDTO);
        Assertions.assertEquals(reviewDTO, actual);
    }
}
