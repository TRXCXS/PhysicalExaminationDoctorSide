package com.example.backend;

import com.example.backend.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {
    @Autowired
    private DoctorService doctorService;

    @Test
    void contextLoads() {
        System.out.println(doctorService.getDoctorByDocCode("ssm"));
        System.out.println(doctorService.getDoctorByDocCode("bq"));
    }
}
