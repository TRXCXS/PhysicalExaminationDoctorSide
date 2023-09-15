package com.example.backend.dto;

import com.example.backend.entity.CheckItem;
import com.example.backend.entity.CiDetailedReport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * 返回报告页面的DTO，由CheckItem和对应的CheckItemDetailedReport组成
 */

public class CheckItemAndCheckItemDetailedReportDTO {
    CheckItem checkItem;
    List<CiDetailedReport> ciDetailedReportList;
}
