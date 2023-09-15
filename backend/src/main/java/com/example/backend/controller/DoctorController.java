package com.example.backend.controller;

import com.example.backend.dto.DoctorLoginFormDTO;
import com.example.backend.entity.Doctor;
import com.example.backend.service.DoctorService;
import com.example.backend.utils.Result;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    /**
     * 医生登录接口
     *
     * @param doctorLoginFormDTO：医生登录表单DTO
     * @return Result封装查找结果，查找成功则是医生对象，否则是null
     * @throws Exception
     */
    @PostMapping("/login")
    public Result login(@RequestBody DoctorLoginFormDTO doctorLoginFormDTO) throws Exception {
        return doctorService.getDoctorByDocCodeAndPassword(doctorLoginFormDTO);
    }

    /**
     * 判断医生是否存在的接口
     *
     * @param doctor：医生对象
     * @return Result封装查找结果，查找成功则是医生对象，否则是null
     */
    @RequestMapping("/isExists")
    public Result isExists(@RequestBody @NotNull Doctor doctor) {
        return doctorService.getDoctorByDocCode(doctor.getDocCode());
    }

    /**
     * 保存医生对象接口
     *
     * @param doctor：医生对象
     * @return 返回对象Result表示是否成功在数据库中保存数据
     * @throws Exception
     */
    @RequestMapping("/register")
    public Result register(@RequestBody Doctor doctor) throws Exception {
        return doctorService.saveDoctor(doctor);
    }
}