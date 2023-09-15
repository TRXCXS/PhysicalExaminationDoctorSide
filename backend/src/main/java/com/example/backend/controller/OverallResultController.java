package com.example.backend.controller;

import com.example.backend.entity.OverallResult;
import com.example.backend.service.OverallResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/overallresult")
@RequiredArgsConstructor
public class OverallResultController {
    private final OverallResultService overallResultService;

    @RequestMapping("/createOverallResult")
    public Boolean createOverallResult(@RequestBody OverallResult overallResult) {
        return overallResultService.createOverallResult(overallResult);
    }

    @RequestMapping("/getOverallResultByOrderId")
    public OverallResult getOverallResultByOrderId(@RequestParam Integer orderId) {
        return overallResultService.getOverallResultByOrderId(orderId);
    }
}
