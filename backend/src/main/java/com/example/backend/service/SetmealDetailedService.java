package com.example.backend.service;

import com.example.backend.entity.SetmealDetailed;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SetmealDetailedService extends MPJBaseService<SetmealDetailed> {
    List<Integer> getCheckItemIdListBySetmealId(int setmealId);
}
