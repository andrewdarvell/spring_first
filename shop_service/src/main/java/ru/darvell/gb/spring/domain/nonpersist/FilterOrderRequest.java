package ru.darvell.gb.spring.domain.nonpersist;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilterOrderRequest {
    private int currPage = 0;
    private int pageSize = 5;

    private Sort.Direction sortDirection = Sort.Direction.DESC;
    private String sortField = "id";

    private Long userId;

}
