package com.example.backend.service;

import com.example.backend.entity.SetmealDetailed;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * SetmealDetailedService
 */
public interface SetmealDetailedService extends MPJBaseService<SetmealDetailed> {
    /**
     * 根据套餐Id获得对应的检查大项的Id的集合
     *
     * @param setmealId：套餐Id
     * @return 返回套餐对应的检查大项的Id的集合
     */
    List<Integer> getCheckItemIdListBySetmealId(int setmealId);
}
