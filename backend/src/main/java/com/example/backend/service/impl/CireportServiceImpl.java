package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.CheckItem;
import com.example.backend.entity.CheckItemDetailed;
import com.example.backend.entity.CiDetailedReport;
import com.example.backend.entity.Cireport;
import com.example.backend.mapper.CiDetailedReportMapper;
import com.example.backend.mapper.CireportMapper;
import com.example.backend.service.*;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CireportServiceImpl extends MPJBaseServiceImpl<CireportMapper, Cireport> implements CireportService {
    private final CireportMapper cireportMapper;
    private final CiDetailedReportMapper ciDetailedReportMapper;
    private final OrderService orderService;
    private final SetmealDetailedService setmealDetailedService;
    private final CheckItemService checkItemService;
    private final CheckItemDetailedService checkItemDetailedService;

    @Override
    @Transactional
    public Result createReportTemplate(Integer orderId) {
        /**
         * 如果cireport表中有了模版数据，那么checkitemdetailed也一定有了。
         */
        if (cireportMapper.exists(
                new QueryWrapper<Cireport>()
                        .eq("orderId", orderId)
        )) {
            return Result.success();
        }

        int setmealId = orderService.getSmIdByOrderId(orderId);
        List<Integer> checkItemIdList = setmealDetailedService.getCheckItemIdListBySetmealId(setmealId);
        List<CheckItem> checkItemList = checkItemService.getCheckItemListByIdList(checkItemIdList);

        for (CheckItem checkItem : checkItemList) {
            Cireport cireport = new Cireport();
            cireport.setCiId(checkItem.getCiId());
            cireport.setCiName(checkItem.getCiName());
            cireport.setOrderId(orderId);

            int state = cireportMapper.insert(cireport);
            if (state == 0) {
                return Result.fail("插入cireport表出错！");
            }

            List<CheckItemDetailed> checkItemDetailedList = checkItemDetailedService.getCheckItemDetailedByCheckItemId(checkItem.getCiId());
            for (CheckItemDetailed checkItemDetailed : checkItemDetailedList) {
                CiDetailedReport ciDetailedReport = new CiDetailedReport();
                ciDetailedReport.setName(checkItemDetailed.getName());
                ciDetailedReport.setUnit(checkItemDetailed.getUnit());
                ciDetailedReport.setMinrange(checkItemDetailed.getMinrange());
                ciDetailedReport.setMaxrange(checkItemDetailed.getMaxrange());
                ciDetailedReport.setNormalValue(checkItemDetailed.getNormalValue());
                ciDetailedReport.setNormalValueString(checkItemDetailed.getNormalValueString());
                ciDetailedReport.setType(checkItemDetailed.getType());
                ciDetailedReport.setValue(null);
                ciDetailedReport.setIsError(null);
                ciDetailedReport.setCiId(checkItem.getCiId());
                ciDetailedReport.setOrderId(orderId);

                int new_state = ciDetailedReportMapper.insert(ciDetailedReport);
                if (new_state == 0) {
                    return Result.fail("插入cidetailedreport表出错！");
                }
            }
        }
        return Result.success();
    }
}
