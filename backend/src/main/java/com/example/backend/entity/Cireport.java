package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cireport")
/**
 * cireport表对应的实体类
 */
public class Cireport {
    /**
     * 检查项编号
     */
    @TableId("ciId")
    private Integer ciId;

    /**
     * 检查项名称
     */
    private String ciName;

    /**
     * 检查项报告主键
     */
    private Integer cirId;

    /**
     * 所属预约编号
     */
    private Integer orderId;
}
