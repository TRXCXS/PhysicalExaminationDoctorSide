package com.example.backend.service;

import com.example.backend.entity.CheckItem;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CheckItemService extends MPJBaseService<CheckItem> {
    CheckItem getCheckItemById(Integer checkItemId);

    List<CheckItem> getCheckItemListByIdList(List<Integer> checkItemIdList);
}
