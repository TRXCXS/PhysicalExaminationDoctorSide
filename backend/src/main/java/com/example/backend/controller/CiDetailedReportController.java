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

    @RequestMapping("/getAllCheckitemAndCheckitemDetailedReportByOrderId")
    public List<CheckItemAndCheckItemDetailedReportDTO> getAllCheckitemAndCheckitemDetailedReportByOrderId(@RequestBody Integer orderId) {
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

    @RequestMapping("/updateCiDetailedReportList")
    public Result updateCiDetailedReportList(@RequestBody List<CiDetailedReport> ciDetailedReportList) {
        return ciDetailedReportService.updateCiDetailedReportList(ciDetailedReportList);
    }
}
