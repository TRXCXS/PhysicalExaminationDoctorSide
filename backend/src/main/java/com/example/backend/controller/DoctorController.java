package com.example.backend.controller;

import com.example.backend.dto.DoctorLoginFormDTO;
import com.example.backend.service.DoctorService;
import com.example.backend.utils.Result;
import lombok.RequiredArgsConstructor;
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
    public Result login(@RequestBody DoctorLoginFormDTO doctorLoginFormDTO) {
        return doctorService.getDoctorByDocCodeAndPassword(doctorLoginFormDTO);
    }
}