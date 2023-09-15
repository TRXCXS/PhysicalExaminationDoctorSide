package com.example.backend.service;

import com.example.backend.entity.CheckItem;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * CheckItemService
 */
public interface CheckItemService extends MPJBaseService<CheckItem> {
    /**
     * 根据checkItemId获得对应的CheckItem
     *
     * @param checkItemId
     * @return
     */
    CheckItem getCheckItemById(Integer checkItemId);

    /**
     * 根据一个列表的checkItemId获得所有的对应的CheckItem
     *
     * @param checkItemIdList
     * @return
     */
    List<CheckItem> getCheckItemListByIdList(List<Integer> checkItemIdList);
}
