package com.example.backend.service;

import com.example.backend.dto.OrderRequestDTO;
import com.example.backend.entity.Order;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService extends MPJBaseService<Order> {
    int getNumberOfOrdersByOrderRequestDTO(OrderRequestDTO orderRequestDTO);

    List<Order> getOrderListByOrderRequestDTO(OrderRequestDTO orderRequestDTO);

    Result getOrdersByOrderRequestDTO(OrderRequestDTO orderRequestDTO);
}
