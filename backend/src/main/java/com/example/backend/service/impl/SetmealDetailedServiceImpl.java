package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.SetmealDetailed;
import com.example.backend.mapper.SetmealDetailedMapper;
import com.example.backend.service.SetmealDetailedService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SetmealDetailedServiceImpl extends MPJBaseServiceImpl<SetmealDetailedMapper, SetmealDetailed> implements SetmealDetailedService {
    private final SetmealDetailedMapper setmealDetailedMapper;

    @Override
    public List<Integer> getCheckItemIdListBySetmealId(int setmealId) {
        QueryWrapper<SetmealDetailed> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("ciId")
                .eq("smId", setmealId);
        List<Map<String, Object>> list = setmealDetailedMapper.selectMaps(queryWrapper);

        List<Integer> checkItemIdList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            checkItemIdList.add((Integer) map.get("ciId"));
        }

        return checkItemIdList;
    }
}
