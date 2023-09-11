package com.example.backend.service.impl;

import com.example.backend.dto.SelectOrderFormDTO;
import com.example.backend.entity.Order;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.service.OrderService;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends MPJBaseServiceImpl<OrderMapper, Order> implements OrderService {
    private final OrderMapper orderMapper;

    @Override
    public Result getOrderBySelectOrderFormDTO(@NotNull SelectOrderFormDTO selectOrderFormDTO) {
        return null;
    }
}