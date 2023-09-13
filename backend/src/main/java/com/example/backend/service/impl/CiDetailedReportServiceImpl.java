package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.CiDetailedReport;
import com.example.backend.mapper.CiDetailedReportMapper;
import com.example.backend.service.CiDetailedReportService;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public Result updateCiDetailedReportList(@NotNull List<CiDetailedReport> ciDetailedReportList) {
        ciDetailedReportList.forEach(CiDetailedReport -> {
            ciDetailedReportMapper.update(CiDetailedReport, new QueryWrapper<CiDetailedReport>().eq("cidrId", CiDetailedReport.getCidrId()));
        });
        return Result.success();
    }
}
