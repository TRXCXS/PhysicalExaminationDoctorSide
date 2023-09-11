package com.example.backend.service;

import com.example.backend.dto.DoctorLoginFormDTO;
import com.example.backend.entity.Doctor;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

@Service
public interface DoctorService extends MPJBaseService<Doctor> {
    Result getDoctorByDocCodeAndPassword(DoctorLoginFormDTO doctorLoginFormDTO) throws Exception;

    Result getDoctorByDocCode(String docCode);

    Result saveDoctor(Doctor doctor) throws Exception;
}
