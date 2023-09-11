package com.example.backend.controller;

import com.example.backend.dto.DoctorLoginFormDTO;
import com.example.backend.entity.Doctor;
import com.example.backend.entity.User;
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

    @PostMapping("/login")
    public Result login(@RequestBody DoctorLoginFormDTO doctorLoginFormDTO) throws Exception {
        return doctorService.getDoctorByDocCodeAndPassword(doctorLoginFormDTO);
    }

    @RequestMapping("/isExists")
    public Result isExists(@RequestBody @NotNull User user) {
        return doctorService.getDoctorByDocCode(user.getUserId());
    }

    @RequestMapping("/register")
    public Result register(@RequestBody Doctor doctor) throws Exception {
        return doctorService.saveDoctor(doctor);
    }
}