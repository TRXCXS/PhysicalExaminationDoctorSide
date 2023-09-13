package com.example.backend.service;

import com.example.backend.entity.Cireport;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

@Service
public interface CireportService extends MPJBaseService<Cireport> {
    Result createReportTemplate(Integer orderId);
}
