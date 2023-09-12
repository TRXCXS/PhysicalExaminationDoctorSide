package com.example.backend.service.impl;

import com.example.backend.dto.OrderRequestDTO;
import com.example.backend.dto.OrderResponseDTO;
import com.example.backend.entity.Order;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.service.OrderService;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends MPJBaseServiceImpl<OrderMapper, Order> implements OrderService {
    private final OrderMapper orderMapper;

    /**
     * 获取符合orderRequestDTO条件的记录数
     *
     * @param orderRequestDTO
     * @return
     */
    @Override
    public int getNumberOfOrdersByOrderRequestDTO(@NotNull OrderRequestDTO orderRequestDTO) {
        return 0;
    }

    /**
     * 获取符合orderRequestDTO条件的所有记录
     *
     * @param orderRequestDTO
     * @return
     */
    @Override
    public List<Order> getOrderListByOrderRequestDTO(OrderRequestDTO orderRequestDTO) {
        return null;
    }

    @Override
    public Result getOrdersByOrderRequestDTO(@NotNull OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        // 总记录数
        int totalCount = getNumberOfOrdersByOrderRequestDTO(orderRequestDTO);
        if (totalCount < 1) {
            // 没有查询到符合条件的记录
            return Result.fail("没有查询到符合条件的记录！");
        }
        // 设置总记录数
        orderResponseDTO.setTotalCount(totalCount);

        // 总页数
        int totalPageNumber;
        if (totalCount % orderRequestDTO.getMaxLineNumberOfPage() == 0) {
            // 刚好被完整分页，每一页都是满的
            totalPageNumber = totalCount / orderRequestDTO.getMaxLineNumberOfPage();
        } else {
            totalPageNumber = totalCount / orderRequestDTO.getMaxLineNumberOfPage() + 1;
        }
        // 设置总页数
        orderResponseDTO.setTotalPageNumber(totalPageNumber);

        // 根据当前页和总页数的关系，计算和设置上一页和下一页
        int currentPageNumber = orderRequestDTO.getCurrentPageNumber();
        if (currentPageNumber > 1) {
            orderResponseDTO.setPrePageNumber(currentPageNumber - 1);
        } else {
            orderResponseDTO.setPrePageNumber(currentPageNumber);
        }
        if (currentPageNumber < totalPageNumber) {
            orderResponseDTO.setNextPageNumber(currentPageNumber + 1);
        } else {
            orderResponseDTO.setNextPageNumber(currentPageNumber);
        }

        // 根据orderRequestDTO设置orderResponseDTO的当前页和每页最大行数
        orderResponseDTO.setCurrentPageNumber(orderRequestDTO.getCurrentPageNumber());
        orderResponseDTO.setMaxLineNumberOfPage(orderRequestDTO.getMaxLineNumberOfPage());

        // 计算查询开始的索引：第1页从0开始，第2页从10开始...
        int beginIndex = (orderRequestDTO.getCurrentPageNumber() - 1) * orderRequestDTO.getMaxLineNumberOfPage();
        // 设置orderRequestDTO的查询开始的索引
        orderRequestDTO.setBeginIndex(beginIndex);

        // 查询记录列表
        List<Order> orderList = getOrderListByOrderRequestDTO(orderRequestDTO);
        orderResponseDTO.setOrderList(orderList);

        return Result.success(orderResponseDTO);
    }
}