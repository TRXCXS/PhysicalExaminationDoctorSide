package com.example.backend.service;

import com.example.backend.entity.OverallResult;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

@Service
public interface OverallResultService extends MPJBaseService<OverallResult> {
    Boolean createOverallResult(OverallResult overallResult);

    OverallResult getOverallResultByOrderId(Integer orderId);
}
