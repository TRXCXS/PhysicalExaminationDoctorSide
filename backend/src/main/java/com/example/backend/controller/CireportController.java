package com.example.backend.controller;

import com.example.backend.service.CireportService;
import com.example.backend.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cireport")
@RequiredArgsConstructor
public class CireportController {
    private final CireportService cireportService;

    @RequestMapping("/createReportTemplate")
    public Result createReportTemplate(@RequestParam Integer orderId) {
        return cireportService.createReportTemplate(orderId);
    }
}
