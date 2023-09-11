package com.example.backend.service;

import com.example.backend.entity.Setmeal;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

@Service
public interface SetmealService extends MPJBaseService<Setmeal> {
    Result getAllSetmeal();
}
