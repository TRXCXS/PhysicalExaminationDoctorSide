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

    /**
     * 根据checkItemId和orderId获得所有的CiDetailedReport组成的列表
     *
     * @param checkItemId：检查大项Id
     * @param orderId：订单编号Id
     * @return List<CiDetailedReport>：CiDetailedReport组成的列表
     */
    @Override
    public List<CiDetailedReport> getCheckItemDetailedReportByCheckItemId(Integer checkItemId, Integer orderId) {
        return ciDetailedReportMapper.selectList(
                new QueryWrapper<CiDetailedReport>().eq("ciId", checkItemId).eq("orderId", orderId)
        );
    }

    /**
     * 批量更新检查小项列表
     *
     * @param ciDetailedReportList：待更新的检查小项列表
     * @return 更新结果
     */
    @Override
    @Transactional
    public Result updateCiDetailedReportList(@NotNull List<CiDetailedReport> ciDetailedReportList) {
        // TODO: 2023/9/15 下面的更新要优化为批量更新
        ciDetailedReportList.forEach(CiDetailedReport -> ciDetailedReportMapper.update(CiDetailedReport, new QueryWrapper<CiDetailedReport>().eq("cidrId", CiDetailedReport.getCidrId())));
        return Result.success();
    }
}
