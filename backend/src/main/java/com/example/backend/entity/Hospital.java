package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("hospital")
/**
 * hospital表对应的实体类
 */
public class Hospital {
    /**
     * 医院编号
     */
    @TableId("hpId")
    private Integer hpId;

    /**
     * 医院名称
     */
    private String name;

    /**
     * 医院图片
     */
    private String picture;

    /**
     * 医院电话
     */
    private String telephone;

    /**
     * 医院地址
     */
    private String address;

    /**
     * 营业时间
     */
    private String businessHours;

    /**
     * 采血截止时间
     */
    private String deadline;

    /**
     * 预约人数规则
     */
    private String rule;

    /**
     * 医院状态（1：正常；2：其他）
     */
    private Integer state;
}
