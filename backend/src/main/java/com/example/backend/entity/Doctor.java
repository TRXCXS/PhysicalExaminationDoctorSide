package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("doctor")
public class Doctor {
    @TableId("docId")
    private Integer docId;
    private String docCode;
    private String realName;
    private String password;
    private Integer sex;
    private Integer deptno;
}
