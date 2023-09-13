package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
    @TableId(value = "docId", type = IdType.AUTO)
    private Integer docId;
    private String docCode;
    private String realName;
    private String password;
    private Integer sex;
    private Integer deptno;

    public Doctor(String docCode, String realName, String password, Integer sex, Integer deptno) {
        this.docCode = docCode;
        this.realName = realName;
        this.password = password;
        this.sex = sex;
        this.deptno = deptno;
    }
}
