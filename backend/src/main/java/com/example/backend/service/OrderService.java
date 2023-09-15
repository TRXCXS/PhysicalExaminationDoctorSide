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
/**
 * 核心业务：OrderService
 */
public interface OrderService extends MPJBaseService<Order> {
    /**
     * 根据传入的DTO为wrapper添加eq条件
     *
     * @param mpjLambdaWrapper
     * @param orderRequestDTO
     */
    public void checkOrderRequestDTO(MPJLambdaWrapper<Order> mpjLambdaWrapper, OrderRequestDTO orderRequestDTO);

    /**
     * 获取符合条件的记录数，避免不必要的联表查询
     *
     * @param orderRequestDTO
     * @return
     */
    int getNumberOfOrdersByOrderRequestDTO(OrderRequestDTO orderRequestDTO);

    /**
     * 根据传入的DTO获得查询字段组成的DTOList
     *
     * @param orderRequestDTO
     * @return
     */
    List<OrderResponseDTOBody> getOrderResponseDTOBodyListByOrderRequestDTO(OrderRequestDTO orderRequestDTO);

    /**
     * 根据传入的DTO查询订单
     *
     * @param orderRequestDTO
     * @return
     */
    Result getOrdersByOrderRequestDTO(OrderRequestDTO orderRequestDTO);

    /**
     * 判断订单是否归档
     *
     * @param orderId
     * @return
     */
    Boolean isOrderArchived(Integer orderId);

    /**
     * 获取订单对应的套餐Id
     *
     * @param orderId
     * @return
     */
    Integer getSmIdByOrderId(Integer orderId);

    /**
     * 订单归档
     *
     * @param orderId
     * @return
     */
    Boolean archiveOrder(Integer orderId);
}