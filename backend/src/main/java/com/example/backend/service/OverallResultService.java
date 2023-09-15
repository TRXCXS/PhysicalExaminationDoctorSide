package com.example.backend.service;

import com.example.backend.entity.OverallResult;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

@Service
/**
 * OverallResultService
 */
public interface OverallResultService extends MPJBaseService<OverallResult> {
    /**
     * 保存总检结论
     *
     * @param overallResult
     * @return 返回Boolean值表示是否保存成功
     */
    Boolean createOverallResult(OverallResult overallResult);

    /**
     * 获取总检结论
     *
     * @param orderId
     * @return 查找到总检结论则返回总检结论对象，否则返回null
     */
    OverallResult getOverallResultByOrderId(Integer orderId);
}
