package com.example.backend.service;

import com.example.backend.entity.CiDetailedReport;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CiDetailedReportService extends MPJBaseService<CiDetailedReport> {
    List<CiDetailedReport> getCheckItemDetailedReportByCheckItemId(Integer checkItemId);
}
