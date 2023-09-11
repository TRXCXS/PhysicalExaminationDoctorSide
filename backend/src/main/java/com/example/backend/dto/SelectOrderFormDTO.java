package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectOrderFormDTO {
    private String userId;
    private String realName;
    // Integer
    private String sex;
    // Integer
    private String smId;
    private String orderDate;
    // Integer
    private String state;
}
