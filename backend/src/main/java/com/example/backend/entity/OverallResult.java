package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("overallresult")
/**
 * overallresult表对应的实体类
 */
public class OverallResult {
    /**
     * 总检结论项编号
     */
    @TableId("orId")
    private Integer orId;

    /**
     * 总检结论项标题
     */
    private String title;

    /**
     * 总检结论项内容
     */
    private String content;

    /**
     * 所属预约编号
     */
    private Integer orderId;

    public OverallResult(String title, String content, Integer orderId) {
        this.title = title;
        this.content = content;
        this.orderId = orderId;
    }
}
