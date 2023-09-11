package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class Order {
    @TableId("orderId")
    private Integer orderId;

    private String orderDate;

    private String userId;

    private Integer hpId;

    private Integer smId;

    private Integer state;
}
