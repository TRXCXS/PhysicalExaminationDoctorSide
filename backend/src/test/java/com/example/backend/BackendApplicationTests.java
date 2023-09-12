package com.example.backend;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.dto.OrderRequestDTO;
import com.example.backend.dto.OrderResponseDTOBody;
import com.example.backend.entity.*;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.service.DoctorService;
import com.example.backend.service.SetmealService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BackendApplicationTests {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private OrderMapper orderMapper;

    @Test
    void doctorService() throws Exception {
//        System.out.println(doctorService.getDoctorByDocCode("ssm"));
//        System.out.println(doctorService.getDoctorByDocCode("bq"));
//
//        System.out.println(SHA256.encrypt("123"));
//
//        System.out.println(doctorService.getDoctorByDocCodeAndPassword(
//                new DoctorLoginFormDTO("ssm", "123")
//        ));
//        System.out.println(doctorService.getDoctorByDocCodeAndPassword(
//                new DoctorLoginFormDTO("ssm", "456")
//        ));

//        doctorService.saveDoctor(
//                new Doctor(
//
//                        "bq",
//                        "扁鹊",
//                        "000",
//                        1,
//                        1
//                )
//        );



//        System.out.println(doctorService.getDoctorByDocCodeAndPassword(
//                new DoctorLoginFormDTO("bq", "000")
//        ));
    }

    @Test
    void SetmealServiceTest() {

        System.out.println("999999999999999999");
        System.out.println(setmealService.getAllSetmeal());
    }

    @Test
    void OrderQueryTest() {
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(
                "",
                "",
                -1,
                -1,
                "",
                -1,

                1,
                3,
                0
        );

        MPJLambdaWrapper<Order> mpjLambdaWrapper = new MPJLambdaWrapper<Order>()
                .select(Order::getOrderId)
                .select(User::getUserId, User::getRealName, User::getSex)
                .selectAs(Setmeal::getName, OrderResponseDTOBody::getSetmealName)
                .selectAs(Hospital::getName, OrderResponseDTOBody::getHospitalName)
                .select(Order::getOrderDate)

                .leftJoin(User.class, User::getUserId, Order::getUserId)
                .leftJoin(Setmeal.class, Setmeal::getSmId, Order::getSmId)
                .leftJoin(Hospital.class, Hospital::getHpId, Order::getHpId);

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

        IPage<OrderResponseDTOBody> iPage = orderMapper.selectJoinPage(
                new Page<>(orderRequestDTO.getBeginIndex(), orderRequestDTO.getMaxLineNumberOfPage()),
                OrderResponseDTOBody.class,
                mpjLambdaWrapper
        );

        List<OrderResponseDTOBody> records = iPage.getRecords();

        System.out.println(records);

        int totalCount = records.size(), lastIndex;
        lastIndex = Math.min(orderRequestDTO.getBeginIndex() + orderRequestDTO.getMaxLineNumberOfPage(), totalCount);

        List<OrderResponseDTOBody> orderResponseDTOBodyList = iPage.getRecords().subList(
                orderRequestDTO.getBeginIndex(), lastIndex
        );
        for (OrderResponseDTOBody orderResponseDTOBody : orderResponseDTOBodyList) {
            System.out.println(orderResponseDTOBody);
        }
    }
}
