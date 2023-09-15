package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * 订单查询时返回的DTOBody，封装查询的所有字段
 */

public class OrderResponseDTOBody {
    private Integer orderId;
    private String userId;
    private String realName;
    private Integer sex;
    private String setmealName;
    private String hospitalName;
    private String orderDate;
}
