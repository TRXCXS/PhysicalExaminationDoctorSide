package com.example.backend.service.impl;

import com.example.backend.entity.Cireport;
import com.example.backend.mapper.CireportMapper;
import com.example.backend.service.CireportService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CireportServiceImpl extends MPJBaseServiceImpl<CireportMapper, Cireport> implements CireportService {
    private final CireportMapper cireportMapper;
}
