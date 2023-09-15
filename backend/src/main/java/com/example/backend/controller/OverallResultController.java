package com.example.backend.controller;

import com.example.backend.entity.OverallResult;
import com.example.backend.service.OverallResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/overallresult")
@RequiredArgsConstructor
public class OverallResultController {
    private final OverallResultService overallResultService;

    /**
     * 创建总检结论对象的接口
     *
     * @param overallResult：总检结论对象
     * @return 返回Boolean值表示是否保存总检结论对象成功
     */
    @RequestMapping("/createOverallResult")
    public Boolean createOverallResult(@RequestBody OverallResult overallResult) {
        return overallResultService.createOverallResult(overallResult);
    }

    /**
     * 获取总检结论对象的接口
     *
     * @param orderId：订单Id
     * @return 返回OverallResult表示查询结果，成功返回总检结论对象
     */
    @RequestMapping("/getOverallResultByOrderId")
    public OverallResult getOverallResultByOrderId(@RequestParam Integer orderId) {
        return overallResultService.getOverallResultByOrderId(orderId);
    }

    /**
     * 获取总检结论列表的接口
     *
     * @param orderId：订单Id
     * @return 返回订单Id对应的所有总检结论组成的列表
     */
    @RequestMapping("/getOverallResultListByOrderId")
    public List<OverallResult> getOverallResultListByOrderId(@RequestParam Integer orderId) {
        return overallResultService.getOverallResultListByOrderId(orderId);
    }
}
