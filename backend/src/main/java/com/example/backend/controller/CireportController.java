package com.example.backend.controller;

import com.example.backend.service.CireportService;
import com.example.backend.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cireport")
@RequiredArgsConstructor
public class CireportController {
    private final CireportService cireportService;

    /**
     * 创建报告模板，在医生编辑报告之前在检查大项报告数据库和检查小项数据库中生成可由订单Id唯一确定的数据项
     * 只会生成一次，不会重复
     *
     * @param orderId：订单Id
     * @return Result：表示是否成功生成数据
     */
    @RequestMapping("/createReportTemplate")
    public Result createReportTemplate(@RequestParam Integer orderId) {
        return cireportService.createReportTemplate(orderId);
    }
}
