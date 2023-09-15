package com.example.backend.service;

import com.example.backend.entity.Setmeal;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

@Service
/**
 * SetmealService
 */
public interface SetmealService extends MPJBaseService<Setmeal> {
    /**
     * 获取所有套餐
     *
     * @return 返回Result对象封装所有套餐对象
     */
    Result getAllSetmeal();
}
