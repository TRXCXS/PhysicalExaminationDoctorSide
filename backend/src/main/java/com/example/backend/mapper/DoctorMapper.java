package com.example.backend.mapper;

import com.example.backend.entity.Doctor;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * Doctor类的mapper，继承自MPJBaseMapper
 */
public interface DoctorMapper extends MPJBaseMapper<Doctor> {
}
