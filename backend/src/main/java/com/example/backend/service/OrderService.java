package com.example.backend.service;

import com.example.backend.dto.OrderRequestDTO;
import com.example.backend.entity.Order;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

@Service
public interface OrderService extends MPJBaseService<Order> {
    Result getOrdersByOrderRequestDTO(OrderRequestDTO orderRequestDTO);
}
