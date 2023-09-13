package com.example.backend.service.impl;

import com.example.backend.entity.CiDetailedReport;
import com.example.backend.mapper.CiDetailedReportMapper;
import com.example.backend.service.CiDetailedReportService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CiDetailedReportServiceImpl extends MPJBaseServiceImpl<CiDetailedReportMapper, CiDetailedReport> implements CiDetailedReportService {
    private final CiDetailedReportMapper ciDetailedReportMapper;
}
