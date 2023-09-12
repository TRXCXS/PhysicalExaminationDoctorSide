package com.example.backend;

import com.example.backend.dto.DoctorLoginFormDTO;
import com.example.backend.entity.Doctor;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.service.DoctorService;
import com.example.backend.service.SetmealService;
import com.example.backend.utils.SHA256;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void MPJTest() {
//        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(
//
//        );
//
//        MPJLambdaWrapper<Order> mpjLambdaWrapper = new MPJLambdaWrapper<>();
//
//        mpjLambdaWrapper
//                .select(Order::getOrderId)
//                .select(User::getUserId, User::getRealName, User::getSex)
//                .selectAs(Setmeal::getName, OrderResponseDTO::getSetmealName)
//                .selectAs(Hospital::getName, OrderResponseDTO::getHospitalName)
//                .select(Order::getOrderDate)
//
//                .leftJoin(User.class, User::getUserId, Order::getUserId)
//                .leftJoin(Setmeal.class, Setmeal::getSmId, Order::getSmId)
//                .leftJoin(Hospital.class, Hospital::getHpId, Order::getHpId)
//
//                .eq(User::getSex, orderRequestDTO.getSex())
//                .eq(Order::getState, orderRequestDTO.getState());
//
//        if (!orderRequestDTO.getUserId().equals("")) {
//            mpjLambdaWrapper.like(User::getUserId, orderRequestDTO.getUserId());
//        }
//        if (!orderRequestDTO.getRealName().equals("")) {
//            mpjLambdaWrapper.like(User::getRealName, orderRequestDTO.getRealName());
//        }
//        if (orderRequestDTO.getSmId() != 0) {
//            mpjLambdaWrapper.eq(Setmeal::getSmId, orderRequestDTO.getSmId());
//        }
//        if (!orderRequestDTO.getOrderDate().equals("")) {
//            mpjLambdaWrapper.eq(Order::getOrderDate, orderRequestDTO.getOrderDate());
//        }
//
//        List<OrderResponseDTO> list = orderMapper.selectJoinList(OrderResponseDTO.class, mpjLambdaWrapper);
//        System.out.println(list);
    }
}
