package com.example.backend.service;

import com.example.backend.entity.CiDetailedReport;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * CiDetailedReportService
 */
public interface CiDetailedReportService extends MPJBaseService<CiDetailedReport> {
    /**
     * 根据checkItemId和orderId获得对应的checkItemDetailedReport
     *
     * @param checkItemId
     * @param orderId
     * @return
     */
    List<CiDetailedReport> getCheckItemDetailedReportByCheckItemId(Integer checkItemId, Integer orderId);

    /**
     * 更新checkItemDetailedReportList
     *
     * @param ciDetailedReportList
     * @return
     */
    Result updateCiDetailedReportList(List<CiDetailedReport> ciDetailedReportList);
}
