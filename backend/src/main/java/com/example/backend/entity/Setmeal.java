package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("setmeal")
/**
 * setmeal表对应的实体类
 */
public class Setmeal {
    /**
     * 套餐编号
     */
    @TableId("smId")
    private Integer smId;

    /**
     * 套餐名称
     */
    private String name;

    /**
     * 套餐类型（1：男士餐套；0：女士套餐）
     */
    private Integer type;

    /**
     * 套餐价格
     */
    private Integer price;
}
