package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * 用户登录表单DTO
 */

public class UserLoginFormDTO {
    private String userId;
    private String password;
}
