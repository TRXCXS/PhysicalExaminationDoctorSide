package com.example.backend.dto;

import com.example.backend.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private List<Order> orderList;

    private Integer currentPageNumber;
    private Integer totalCount;
    private Integer maxPageNumber;
    private Integer prePageNumber;
    private Integer nextPageNumber;
}
