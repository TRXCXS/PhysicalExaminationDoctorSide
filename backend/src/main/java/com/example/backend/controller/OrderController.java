package com.example.backend.controller;

import com.example.backend.dto.OrderRequestDTO;
import com.example.backend.service.OrderService;
import com.example.backend.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @RequestMapping("/getOrdersByOrderRequestDTO")
    public Result getOrdersByOrderRequestDTO(@RequestBody OrderRequestDTO orderRequestDTO) {
        return orderService.getOrdersByOrderRequestDTO(orderRequestDTO);
    }


    @RequestMapping("/isOrderArchived")
    public Boolean isOrderArchived(@RequestBody Integer orderId) {
        return orderService.isOrderArchived(orderId);
    }

    @RequestMapping("/getSmIdByOrderId")
    public Integer getSmIdByOrderId(@RequestBody Integer orderId) {
        return orderService.getSmIdByOrderId(orderId);
    }
}