package ru.darvell.gb.spring.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.darvell.gb.spring.domain.Review;

@Setter
@Getter
@NoArgsConstructor
public class ReviewDTO {

    private Long id;
    private String pluses;
    private String minuses;
    private byte rating;

    private Long productId;
    private Long userId;
    private String userName;

    public ReviewDTO(Review that) {
        id = that.getId();
        pluses = that.getPluses();
        minuses = that.getMinuses();
        rating = that.getRating();
        productId = that.getProduct().getId();
        userId = that.getUser().getId();
        userName = that.getUser().getLogin();
    }
}
