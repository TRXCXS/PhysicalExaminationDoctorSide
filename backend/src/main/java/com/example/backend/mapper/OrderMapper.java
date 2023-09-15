package com.example.backend.mapper;

import com.example.backend.entity.Order;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * Order类的mapper，继承自MPJBaseMapper
 */
public interface OrderMapper extends MPJBaseMapper<Order> {
}
