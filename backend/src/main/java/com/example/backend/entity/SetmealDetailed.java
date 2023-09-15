package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("setmealdetailed")
/**
 * setmealdetailed表对应的实体类
 */
public class SetmealDetailed {
    /**
     * 套餐明细编号（无意义主键）
     */
    @TableId("sdId")
    private Integer sdId;

    /**
     * 套餐编号
     */
    private Integer smId;

    /**
     * 检查项编号
     */
    private Integer ciId;
}
