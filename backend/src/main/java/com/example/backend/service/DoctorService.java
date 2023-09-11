package com.example.backend.service;

import com.example.backend.dto.DoctorLoginFormDTO;
import com.example.backend.utils.Result;
import org.springframework.stereotype.Service;

@Service
public interface DoctorService  {
    Result getDoctorByDocCodeAndPassword(DoctorLoginFormDTO doctorLoginFormDTO);

    Result getDoctorByDocCode(String docCode);
}
