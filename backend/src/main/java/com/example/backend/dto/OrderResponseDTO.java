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

    // 总记录数
    private Integer totalCount;
    // 总页数
    private Integer totalPageNumber;

    // 上一页数
    private Integer prePageNumber;
    // 下一页数
    private Integer nextPageNumber;

    // 当前页数
    private Integer currentPageNumber;
    // 每页最大行数
    private Integer maxLineNumberOfPage;
}
