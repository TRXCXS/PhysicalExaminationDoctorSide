package com.example.backend.service;

import com.example.backend.entity.CheckItemDetailed;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * CheckItemDetailedService
 */
public interface CheckItemDetailedService extends MPJBaseService<CheckItemDetailed> {
    /**
     * 根据checkItemId获得对应的CheckItemDetailed列表
     *
     * @param checkItemId
     * @return
     */

    List<CheckItemDetailed> getCheckItemDetailedByCheckItemId(Integer checkItemId);
}
