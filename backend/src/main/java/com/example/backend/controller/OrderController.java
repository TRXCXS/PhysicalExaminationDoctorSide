package com.example.backend.controller;

import com.example.backend.dto.OrderRequestDTO;
import com.example.backend.service.OrderService;
import com.example.backend.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * 根据orderRequestDTO查找订单的接口
     *
     * @param orderRequestDTO：封装查询条件的DTO
     * @return 返回Result对象封装订单返回对象DTO列表
     */
    @RequestMapping("/getOrdersByOrderRequestDTO")
    public Result getOrdersByOrderRequestDTO(@RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.getOrdersByOrderRequestDTO(orderRequestDTO);
    }

    /**
     * 判断订单是否已经归档
     *
     * @param orderId：订单Id
     * @return Boolean值表示是否已经归档
     */
    @RequestMapping("/isOrderArchived")
    public Boolean isOrderArchived(@RequestParam Integer orderId) {
        return orderService.isOrderArchived(orderId);
    }

    /**
     * 获得套餐Id的接口
     *
     * @param orderId：订单Id
     * @return 返回套餐Id
     */
    @RequestMapping("/getSmIdByOrderId")
    public Integer getSmIdByOrderId(@RequestParam Integer orderId) {
        return orderService.getSmIdByOrderId(orderId);
    }

    /***
     * 订单归档接口
     *
     * @param orderId：订单Id
     * @return 返回Boolean值表示是否成功归档
     */
    @RequestMapping("/archiveOrder")
    public Boolean archiveOrder(@RequestParam Integer orderId) {
        return orderService.archiveOrder(orderId);
    }
}