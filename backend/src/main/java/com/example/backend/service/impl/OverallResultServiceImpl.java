package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.OverallResult;
import com.example.backend.mapper.OverallResultMapper;
import com.example.backend.service.OverallResultService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OverallResultServiceImpl extends MPJBaseServiceImpl<OverallResultMapper, OverallResult> implements OverallResultService {
    private final OverallResultMapper overallResultMapper;

    @Override
    public Boolean createOverallResult(OverallResult overallResult) {
        int state = overallResultMapper.insert(overallResult);

        if (state == 1) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public OverallResult getOverallResultByOrderId(Integer orderId) {
        return overallResultMapper.selectOne(
                new QueryWrapper<OverallResult>().eq("orderId", orderId)
        );
    }
}
