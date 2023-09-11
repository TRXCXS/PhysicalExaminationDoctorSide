package com.example.backend;

import com.example.backend.dto.DoctorLoginFormDTO;
import com.example.backend.dto.OrderDTO;
import com.example.backend.dto.SelectOrderFormDTO;
import com.example.backend.entity.*;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.service.DoctorService;
import com.example.backend.service.SetmealService;
import com.example.backend.utils.SHA256;
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
    void doctorServiceTest() throws Exception {
        System.out.println(doctorService.getDoctorByDocCode("ssm"));
        System.out.println(doctorService.getDoctorByDocCode("bq"));

        System.out.println(SHA256.encrypt("123"));

        System.out.println(doctorService.getDoctorByDocCodeAndPassword(
                new DoctorLoginFormDTO("ssm", "123")
        ));
        System.out.println(doctorService.getDoctorByDocCodeAndPassword(
                new DoctorLoginFormDTO("ssm", "456")
        ));

        doctorService.saveDoctor(
                new Doctor(
                        100,
                        "bq",
                        "扁鹊",
                        "000",
                        1,
                        1
                )
        );

        System.out.println(doctorService.getDoctorByDocCodeAndPassword(
                new DoctorLoginFormDTO("bq", "000")
        ));
    }

    @Test
    void SetmealServiceTest() {
        System.out.println(setmealService.getAllSetmeal());
    }

    @Test
    void MPJTest(SelectOrderFormDTO selectOrderFormDTO) {
        MPJLambdaWrapper<Order> mpjLambdaWrapper = new MPJLambdaWrapper<>();

        mpjLambdaWrapper
                .select(Order::getOrderId)
                .select(User::getUserId, User::getRealName, User::getSex)
                .selectAs(Setmeal::getName, OrderDTO::getSetmealName)
                .selectAs(Hospital::getName, OrderDTO::getHospitalName)
                .select(Order::getOrderDate)

                .leftJoin(User.class, User::getUserId, Order::getUserId)
                .leftJoin(Setmeal.class, Setmeal::getSmId, Order::getSmId)
                .leftJoin(Hospital.class, Hospital::getHpId, Order::getHpId)

                .eq(User::getSex, selectOrderFormDTO.getSex())
                .eq(Setmeal::getSmId, selectOrderFormDTO.getSmId())
                .eq(Order::getState, selectOrderFormDTO.getState());

        if (!selectOrderFormDTO.getUserId().equals("")) {
            mpjLambdaWrapper.like(User::getUserId, selectOrderFormDTO.getUserId());
        }
        if (!selectOrderFormDTO.getRealName().equals("")) {
            mpjLambdaWrapper.like(User::getRealName, selectOrderFormDTO.getRealName());
        }
        if (!selectOrderFormDTO.getOrderDate().equals("")) {
            mpjLambdaWrapper.eq(Order::getOrderDate, selectOrderFormDTO.getOrderDate());
        }

        List<OrderDTO> list = orderMapper.selectJoinList(OrderDTO.class, mpjLambdaWrapper);
        System.out.println(list);
    }
}
