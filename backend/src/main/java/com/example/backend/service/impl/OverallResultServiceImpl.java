package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.OverallResult;
import com.example.backend.mapper.OverallResultMapper;
import com.example.backend.service.OverallResultService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OverallResultServiceImpl extends MPJBaseServiceImpl<OverallResultMapper, OverallResult> implements OverallResultService {
    private final OverallResultMapper overallResultMapper;

    /**
     * 创建总检结论
     *
     * @param overallResult：总检结论对象
     * @return Boolean值表示是否已经创建成功
     */
    @Override
    @Transactional
    public Boolean createOverallResult(OverallResult overallResult) {
        int state = overallResultMapper.insert(overallResult);

        if (state == 1) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * 查看总检结论
     *
     * @param orderId：订单Id
     * @return OverallResult表示查找结果，找到则返回总检结论对象，否则返回null
     */
    @Override
    public OverallResult getOverallResultByOrderId(Integer orderId) {
        return overallResultMapper.selectOne(
                new QueryWrapper<OverallResult>().eq("orderId", orderId)
        );
    }

    /**
     * 获取总检结论列表
     *
     * @param orderId：订单Id
     * @return 获取订单Id对应的总检结论列表
     */
    @Override
    public List<OverallResult> getOverallResultListByOrderId(Integer orderId) {
        return overallResultMapper.selectList(
                new QueryWrapper<OverallResult>().eq("orderId", orderId)
        );
    }
}
