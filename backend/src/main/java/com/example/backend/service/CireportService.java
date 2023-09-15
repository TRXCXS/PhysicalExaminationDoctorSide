package com.example.backend.service;

import com.example.backend.entity.Cireport;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

@Service
/**
 * CireportService
 */
public interface CireportService extends MPJBaseService<Cireport> {
    /**
     * 创建报告模板
     *
     * @param orderId
     * @return
     */
    Result createReportTemplate(Integer orderId);
}
