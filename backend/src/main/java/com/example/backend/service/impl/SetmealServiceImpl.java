package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.Setmeal;
import com.example.backend.mapper.SetmealMapper;
import com.example.backend.service.SetmealService;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SetmealServiceImpl extends MPJBaseServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    private final SetmealMapper setmealMapper;

    /**
     * 获取所有的套餐对象
     *
     * @return 所有的套餐对象组成的列表
     */
    @Override
    public Result getAllSetmeal() {
        QueryWrapper<Setmeal> queryWrapper = new QueryWrapper<>();
        List<Setmeal> setmealList = setmealMapper.selectList(queryWrapper);

        return Result.success(setmealList, (long) setmealList.size());
    }
}
