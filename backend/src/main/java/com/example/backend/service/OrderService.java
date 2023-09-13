package com.example.backend.service;

import com.example.backend.dto.OrderRequestDTO;
import com.example.backend.dto.OrderResponseDTOBody;
import com.example.backend.entity.Order;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService extends MPJBaseService<Order> {
    public void checkOrderRequestDTO(MPJLambdaWrapper<Order> mpjLambdaWrapper, OrderRequestDTO orderRequestDTO);

    int getNumberOfOrdersByOrderRequestDTO(OrderRequestDTO orderRequestDTO);

    List<OrderResponseDTOBody> getOrderResponseDTOBodyListByOrderRequestDTO(OrderRequestDTO orderRequestDTO);

    Result getOrdersByOrderRequestDTO(OrderRequestDTO orderRequestDTO);

    Boolean isOrderArchived(Integer orderId);
}