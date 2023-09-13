package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.dto.OrderRequestDTO;
import com.example.backend.dto.OrderResponseDTO;
import com.example.backend.dto.OrderResponseDTOBody;
import com.example.backend.entity.Hospital;
import com.example.backend.entity.Order;
import com.example.backend.entity.Setmeal;
import com.example.backend.entity.User;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.service.OrderService;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends MPJBaseServiceImpl<OrderMapper, Order> implements OrderService {
    private final OrderMapper orderMapper;

    @Override
    public void checkOrderRequestDTO(MPJLambdaWrapper<Order> mpjLambdaWrapper, @NotNull OrderRequestDTO orderRequestDTO) {
        if (!orderRequestDTO.getUserId().equals("")) {
            mpjLambdaWrapper.like(User::getUserId, orderRequestDTO.getUserId());
        }
        if (!orderRequestDTO.getRealName().equals("")) {
            mpjLambdaWrapper.like(User::getRealName, orderRequestDTO.getRealName());
        }
        if (orderRequestDTO.getSex() != -1) {
            mpjLambdaWrapper.eq(User::getSex, orderRequestDTO.getSex());
        }
        if (orderRequestDTO.getSmId() != -1) {
            mpjLambdaWrapper.eq(Order::getSmId, orderRequestDTO.getSmId());
        }
        if (!orderRequestDTO.getOrderDate().equals("")) {
            mpjLambdaWrapper.eq(Order::getOrderDate, orderRequestDTO.getOrderDate());
        }
        if (orderRequestDTO.getState() != -1) {
            mpjLambdaWrapper.eq(Order::getState, orderRequestDTO.getState());
        }
    }

    /**
     * 获取符合orderRequestDTO条件的记录数
     *
     * @param orderRequestDTO
     * @return
     */
    @Override
    public int getNumberOfOrdersByOrderRequestDTO(@NotNull OrderRequestDTO orderRequestDTO) {
        MPJLambdaWrapper<Order> mpjLambdaWrapper = new MPJLambdaWrapper<Order>()
                .leftJoin(User.class, User::getUserId, Order::getUserId);

        checkOrderRequestDTO(mpjLambdaWrapper, orderRequestDTO);

        return Math.toIntExact(orderMapper.selectCount(mpjLambdaWrapper));
    }

    /**
     * 获取符合orderRequestDTO条件的所有记录
     *
     * @param orderRequestDTO
     * @return
     */
    @Override
    public List<OrderResponseDTOBody> getOrderResponseDTOBodyListByOrderRequestDTO(OrderRequestDTO orderRequestDTO) {
        MPJLambdaWrapper<Order> mpjLambdaWrapper = new MPJLambdaWrapper<Order>()
                .select(Order::getOrderId)
                .select(User::getUserId, User::getRealName, User::getSex)
                .selectAs(Setmeal::getName, OrderResponseDTOBody::getSetmealName)
                .selectAs(Hospital::getName, OrderResponseDTOBody::getHospitalName)
                .select(Order::getOrderDate)

                .leftJoin(User.class, User::getUserId, Order::getUserId)
                .leftJoin(Setmeal.class, Setmeal::getSmId, Order::getSmId)
                .leftJoin(Hospital.class, Hospital::getHpId, Order::getHpId);

        checkOrderRequestDTO(mpjLambdaWrapper, orderRequestDTO);

        IPage<OrderResponseDTOBody> iPage = orderMapper.selectJoinPage(
                new Page<>(orderRequestDTO.getBeginIndex(), orderRequestDTO.getMaxLineNumberOfPage()),
                OrderResponseDTOBody.class,
                mpjLambdaWrapper
        );

        List<OrderResponseDTOBody> records = iPage.getRecords();
        int totalCount = records.size(),
                lastIndex = Math.min(orderRequestDTO.getBeginIndex() + orderRequestDTO.getMaxLineNumberOfPage(), totalCount);

        return iPage.getRecords().subList(
                orderRequestDTO.getBeginIndex(), lastIndex
        );
    }

    @Override
    public Result getOrdersByOrderRequestDTO(@NotNull OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        // 总记录数
        int totalCount = getNumberOfOrdersByOrderRequestDTO(orderRequestDTO);
        if (totalCount < 1) {
            // 没有查询到符合条件的记录，返回的Result的data的OrderResponseDTOBodyList属性是一个空列表
            List<OrderResponseDTOBody> orderResponseDTOBodyList = new ArrayList<>();
            orderResponseDTO.setOrderResponseDTOBodyList(orderResponseDTOBodyList);
            return Result.fail("没有查询到符合条件的记录！", orderResponseDTO);
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
        List<OrderResponseDTOBody> orderResponseDTOBodyList = getOrderResponseDTOBodyListByOrderRequestDTO(orderRequestDTO);
        orderResponseDTO.setOrderResponseDTOBodyList(orderResponseDTOBodyList);

        return Result.success(orderResponseDTO);
    }

    @Override
    public Boolean isOrderArchived(Integer orderId) {
        return orderMapper.exists(
                new QueryWrapper<Order>()
                        .eq("orderId", orderId)
                        .eq("state", 2)
        );
    }
}