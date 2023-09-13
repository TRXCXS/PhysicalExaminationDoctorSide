package com.example.backend.service;

import com.example.backend.entity.CheckItemDetailed;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CheckItemDetailedService extends MPJBaseService<CheckItemDetailed> {
    List<CheckItemDetailed> getCheckItemDetailedByCheckItemId(Integer checkItemId);
}
