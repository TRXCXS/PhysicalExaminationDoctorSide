package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.CheckItemDetailed;
import com.example.backend.mapper.CheckItemDetailedMapper;
import com.example.backend.service.CheckItemDetailedService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckItemDetailedServiceImpl extends MPJBaseServiceImpl<CheckItemDetailedMapper, CheckItemDetailed> implements CheckItemDetailedService {
    private final CheckItemDetailedMapper checkItemDetailedMapper;

    /**
     * 根据检查大项Id获得对应的检查小项列表
     *
     * @param checkItemId：检查大项Id
     * @return 检查大项Id获得对应的检查小项列表
     */
    @Override
    public List<CheckItemDetailed> getCheckItemDetailedByCheckItemId(Integer checkItemId) {
        return checkItemDetailedMapper.selectList(
                new QueryWrapper<CheckItemDetailed>().eq("ciId", checkItemId)
        );
    }
}
