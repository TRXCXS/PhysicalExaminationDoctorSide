package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * 医生登录表单DTO
 */

public class DoctorLoginFormDTO {
    private String docCode;
    private String password;
}
