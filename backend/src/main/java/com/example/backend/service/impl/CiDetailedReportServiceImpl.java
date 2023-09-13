package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.CiDetailedReport;
import com.example.backend.mapper.CiDetailedReportMapper;
import com.example.backend.service.CiDetailedReportService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CiDetailedReportServiceImpl extends MPJBaseServiceImpl<CiDetailedReportMapper, CiDetailedReport> implements CiDetailedReportService {
    private final CiDetailedReportMapper ciDetailedReportMapper;

    @Override
    public List<CiDetailedReport> getCheckItemDetailedReportByCheckItemId(Integer checkItemId) {
        return ciDetailedReportMapper.selectList(
                new QueryWrapper<CiDetailedReport>().eq("ciId", checkItemId)
        );
    }
}
