package com.example.backend.controller;

import com.example.backend.dto.CheckItemAndCheckItemDetailedDTO;
import com.example.backend.entity.CheckItem;
import com.example.backend.entity.CheckItemDetailed;
import com.example.backend.service.CheckItemDetailedService;
import com.example.backend.service.CheckItemService;
import com.example.backend.service.SetmealDetailedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/checkitemdetailed")
@RequiredArgsConstructor
public class CheckItemDetailedController {
    private final SetmealDetailedService setmealDetailedService;
    private final CheckItemService checkItemService;
    private final CheckItemDetailedService checkItemDetailedService;

    /**
     * 传入套餐Id，获取Checkitem和CheckitemDetailed列表组成的DTO的列表
     *
     * @param setmealId：套餐Id
     * @return List<CheckItemAndCheckItemDetailedDTO>：Checkitem和CheckitemDetailed列表组成的DTO的列表
     */
    @RequestMapping("/getAllCheckitemAndCheckitemDetailedBySetmealId")
    public List<CheckItemAndCheckItemDetailedDTO> getAllCheckitemAndCheckitemDetailedBySetmealId(@RequestBody Integer setmealId) {
        List<CheckItemAndCheckItemDetailedDTO> checkItemAndCheckItemDetailedDTOList = new ArrayList<>();

        List<Integer> checkItemIdList = setmealDetailedService.getCheckItemIdListBySetmealId(setmealId);

        for (Integer checkItemId : checkItemIdList) {
            CheckItem checkItem = checkItemService.getCheckItemById(checkItemId);
            List<CheckItemDetailed> checkItemDetailedList = checkItemDetailedService.getCheckItemDetailedByCheckItemId(checkItemId);
            CheckItemAndCheckItemDetailedDTO checkItemAndCheckItemDetailedDTO = new CheckItemAndCheckItemDetailedDTO(checkItem, checkItemDetailedList);
            checkItemAndCheckItemDetailedDTOList.add(checkItemAndCheckItemDetailedDTO);
        }

        return checkItemAndCheckItemDetailedDTOList;
    }
}
