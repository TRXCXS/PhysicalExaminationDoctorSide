package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.CheckItem;
import com.example.backend.mapper.CheckItemMapper;
import com.example.backend.service.CheckItemService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckItemServiceImpl extends MPJBaseServiceImpl<CheckItemMapper, CheckItem> implements CheckItemService {
    private final CheckItemMapper checkItemMapper;

    /**
     * 根据checkItemId获得对应的检查大项对象
     *
     * @param checkItemId：检查大项Id
     * @return checkItemId获得对应的检查大项对象
     */
    @Override
    public CheckItem getCheckItemById(Integer checkItemId) {
        return checkItemMapper.selectOne(
                new QueryWrapper<CheckItem>().eq("ciId", checkItemId)
        );
    }

    /**
     * 根据checkItemIdList获得对应的所有CheckItem组成的列表
     *
     * @param checkItemIdList：检查大项Id组成的列表
     * @return List<CheckItem>：所有CheckItem组成的列表
     */
    @Override
    public List<CheckItem> getCheckItemListByIdList(@NotNull List<Integer> checkItemIdList) {
        List<CheckItem> checkItemList = new ArrayList<>();
        for (Integer checkItemId : checkItemIdList) {
            checkItemList.add(getCheckItemById(checkItemId));
        }
        return checkItemList;
    }
}
