package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    /**
     * 预约编号
     */
    private Integer orderId;

    private String userId;
    private String realName;
    private Integer sex;

    private String setmealName;
    private String hospitalName;
    private String orderDate;
}
