package com.example.backend;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.controller.CheckItemDetailedController;
import com.example.backend.dto.DoctorLoginFormDTO;
import com.example.backend.dto.OrderRequestDTO;
import com.example.backend.dto.OrderResponseDTOBody;
import com.example.backend.entity.*;
import com.example.backend.mapper.OrderMapper;
import com.example.backend.service.*;
import com.example.backend.utils.SHA256;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BackendApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private SetmealDetailedService setmealDetailedService;
    @Autowired
    private CheckItemService checkItemService;
    @Autowired
    private CheckItemDetailedService checkItemDetailedService;
    @Autowired
    private CheckItemDetailedController checkItemDetailedController;
    @Autowired
    private CiDetailedReportService ciDetailedReportService;

    @Test
    void saveDoctorTest() throws Exception {
        doctorService.saveDoctor(
                new Doctor(
                        "bq",
                        "扁鹊",
                        "000",
                        1,
                        1
                )
        );
    }

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
                        /**
                         * 第一个字段是自增的，不应该给值。给null也会导致错误的结果。
                         */
                        null,
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
    void setmealServiceTest() {
        System.out.println(setmealService.getAllSetmeal());
    }

    @Test
    void orderQueryTest() {
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(
                "",
                "",
                -1,
                -1,
                "",
                -1,

                5,
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

        orderService.checkOrderRequestDTO(mpjLambdaWrapper, orderRequestDTO);

        IPage<OrderResponseDTOBody> iPage = orderMapper.selectJoinPage(
                new Page<>(orderRequestDTO.getCurrentPageNumber(), orderRequestDTO.getMaxLineNumberOfPage()),
                OrderResponseDTOBody.class,
                mpjLambdaWrapper
        );

        List<OrderResponseDTOBody> records = iPage.getRecords();

        System.out.println("records:");
        for (OrderResponseDTOBody orderResponseDTOBody : records) {
            System.out.println(orderResponseDTOBody);
        }

        /**
         * 使用切片的方法——如果没有配置mybatis-plus分页插件（MyBatisPlusPaginationInnerConfig）的话，会返回所有记录而不是目标那一页
         *
         int totalCount = records.size(), lastIndex;
         lastIndex = Math.min(orderRequestDTO.getBeginIndex() + orderRequestDTO.getMaxLineNumberOfPage(), totalCount);

         List<OrderResponseDTOBody> orderResponseDTOBodyList = iPage.getRecords().subList(
         orderRequestDTO.getBeginIndex(), lastIndex
         );
         for (OrderResponseDTOBody orderResponseDTOBody : orderResponseDTOBodyList) {
         System.out.println(orderResponseDTOBody);
         }
         */

        /**
         * 测试service中的对应方法是否正常工作
         *
         System.out.println("new records:");
         for (OrderResponseDTOBody orderResponseDTOBody : orderService.getOrderResponseDTOBodyListByOrderRequestDTO(orderRequestDTO)) {
         System.out.println(orderResponseDTOBody);
         }
         */
    }

    @Test
    void setmealDetailedServiceTest() {
        System.out.println(setmealDetailedService.getCheckItemIdListBySetmealId(3));
    }

    @Test
    void checkItemServiceTest() {
        System.out.println(checkItemService.getCheckItemById(1));

        /**
         * 根据套餐编号拿到所有的检查大项
         */
        System.out.println(
                checkItemService.getCheckItemListByIdList(
                        setmealDetailedService.getCheckItemIdListBySetmealId(3)
                )
        );
    }

    @Test
    void checkItemDetailedService() {
        System.out.println(
                checkItemDetailedService.getCheckItemDetailedByCheckItemId(1)
        );
    }

    @Test
    void CheckItemDetailedControllerTest() {
        System.out.println(
                checkItemDetailedController.getAllCheckitemAndCheckitemDetailedBySetmealId(1)
        );
    }

    @Test
    void batchUpdateTest() {
        List<CiDetailedReport> ciDetailedReportList = new ArrayList<>();
        CiDetailedReport ciDetailedReport1 = new CiDetailedReport();
        CiDetailedReport ciDetailedReport2 = new CiDetailedReport();

        ciDetailedReport1.setName("鹏程100");
        ciDetailedReport1.setCidrId(1);
        ciDetailedReportList.add(ciDetailedReport1);
        ciDetailedReport2.setName("润填200");
        ciDetailedReport2.setCidrId(2);
        ciDetailedReportList.add(ciDetailedReport2);

        ciDetailedReportService.updateCiDetailedReportList(ciDetailedReportList);
    }

    @Test
    void getUserNamesByFuzzyQueryTest() {
        System.out.println(
                userService.getUserNamesByFuzzyQuery("东方")
        );
    }
}
