package com.example.backend.controller;

import com.example.backend.service.SetmealService;
import com.example.backend.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setmeal")
@RequiredArgsConstructor
public class SetmealController {
    private final SetmealService setmealService;

    /**
     * 获取所有套餐的接口
     *
     * @return：返回所有套餐组成的列表
     */
    @RequestMapping("/getAllSetmeal")
    public Result getAllSetmeal() {
        return setmealService.getAllSetmeal();
    }
}
