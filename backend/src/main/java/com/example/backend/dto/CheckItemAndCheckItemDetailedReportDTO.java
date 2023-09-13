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
public class CheckItemAndCheckItemDetailedReportDTO {
    CheckItem checkItem;
    List<CiDetailedReport> ciDetailedReportList;
}
