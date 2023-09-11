package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("checkitem")
public class CheckItem {
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
     * 检查项内容
     */
    private String ciContent;

    /**
     * 检查项意义
     */
    private String meaning;

    /**
     * 备注
     */
    private String remarks;
}
