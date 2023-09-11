package com.example.backend;

import com.example.backend.dto.DoctorLoginFormDTO;
import com.example.backend.service.DoctorService;
import com.example.backend.utils.SHA256;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {
    @Autowired
    private DoctorService doctorService;

    @Test
    void contextLoads() throws Exception {
        System.out.println(doctorService.getDoctorByDocCode("ssm"));
        System.out.println(doctorService.getDoctorByDocCode("bq"));

        System.out.println(SHA256.encrypt("123"));

        System.out.println(doctorService.getDoctorByDocCodeAndPassword(
                new DoctorLoginFormDTO("ssm", "123")
        ));
        System.out.println(doctorService.getDoctorByDocCodeAndPassword(
                new DoctorLoginFormDTO("ssm", "456")
        ));
    }
}
