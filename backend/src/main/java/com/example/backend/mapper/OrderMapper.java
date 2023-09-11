package com.example.backend.mapper;

import com.example.backend.entity.Order;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends MPJBaseMapper<Order> {
}
