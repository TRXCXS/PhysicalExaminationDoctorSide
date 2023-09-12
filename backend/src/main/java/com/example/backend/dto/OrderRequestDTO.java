package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private String userId;
    private String realName;
    private Integer sex;
    private Integer smId;
    private String orderDate;
    private Integer state;

    private Integer currentPageNumber;
    private Integer maxLineNumberOfPage;
    private Integer beginIndex;
}
