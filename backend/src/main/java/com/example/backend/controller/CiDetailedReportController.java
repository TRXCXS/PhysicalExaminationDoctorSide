package com.example.backend.controller;

import com.example.backend.dto.CheckItemAndCheckItemDetailedReportDTO;
import com.example.backend.entity.CheckItem;
import com.example.backend.entity.CiDetailedReport;
import com.example.backend.service.CheckItemService;
import com.example.backend.service.CiDetailedReportService;
import com.example.backend.service.OrderService;
import com.example.backend.service.SetmealDetailedService;
import com.example.backend.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cidetailedreport")
@RequiredArgsConstructor
public class CiDetailedReportController {
    private final SetmealDetailedService setmealDetailedService;
    private final OrderService orderService;
    private final CheckItemService checkItemService;
    private final CiDetailedReportService ciDetailedReportService;

    /**
     * 根据orderId获得对应的Checkitem和CheckitemDetailedReport列表组成的DTO的列表
     *
     * @param orderId：订单Id
     * @return List<CheckItemAndCheckItemDetailedReportDTO>：订单Id对应的Checkitem和CheckitemDetailedReport列表组成的DTO的列表
     */
    @RequestMapping("/getAllCheckitemAndCheckitemDetailedReportByOrderId")
    public List<CheckItemAndCheckItemDetailedReportDTO> getAllCheckitemAndCheckitemDetailedReportByOrderId(@RequestParam Integer orderId) {
        List<CheckItemAndCheckItemDetailedReportDTO> checkItemAndCheckItemDetailedReportDTOList = new ArrayList<>();

        Integer setmealId = orderService.getSmIdByOrderId(orderId);
        List<Integer> checkItemIdList = setmealDetailedService.getCheckItemIdListBySetmealId(setmealId);

        for (Integer checkItemId : checkItemIdList) {
            CheckItem checkItem = checkItemService.getCheckItemById(checkItemId);
            List<CiDetailedReport> ciDetailedReportList = ciDetailedReportService.getCheckItemDetailedReportByCheckItemId(checkItemId,orderId);
            CheckItemAndCheckItemDetailedReportDTO checkItemAndCheckItemDetailedReportDTO = new CheckItemAndCheckItemDetailedReportDTO(checkItem, ciDetailedReportList);
            checkItemAndCheckItemDetailedReportDTOList.add(checkItemAndCheckItemDetailedReportDTO);
        }

        return checkItemAndCheckItemDetailedReportDTOList;
    }

    /**
     * 传入ciDetailedReportList列表，进行批量更新
     *
     * @param ciDetailedReportList：检查小项列表
     * @return Result封装更新结果
     */
    @RequestMapping("/updateCiDetailedReportList")
    public Result updateCiDetailedReportList(@RequestBody List<CiDetailedReport> ciDetailedReportList) {
        return ciDetailedReportService.updateCiDetailedReportList(ciDetailedReportList);
    }
}
