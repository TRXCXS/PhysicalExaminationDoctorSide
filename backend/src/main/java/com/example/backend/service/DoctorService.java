package com.example.backend.service;

import com.example.backend.dto.DoctorLoginFormDTO;
import com.example.backend.entity.Doctor;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

@Service
/**
 * DoctorService
 */
public interface DoctorService extends MPJBaseService<Doctor> {
    /**
     * 医生登录，返回Result封装结果对象
     *
     * @param doctorLoginFormDTO
     * @return
     * @throws Exception
     */
    Result getDoctorByDocCodeAndPassword(DoctorLoginFormDTO doctorLoginFormDTO) throws Exception;

    /**
     * 根据docCode查找医生对象
     *
     * @param docCode
     * @return
     */
    Result getDoctorByDocCode(String docCode);

    /**
     * 保存新的医生对象
     *
     * @param doctor
     * @return
     * @throws Exception
     */
    Result saveDoctor(Doctor doctor) throws Exception;
}
