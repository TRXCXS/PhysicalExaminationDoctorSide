package com.example.backend.mapper;

import com.example.backend.entity.Hospital;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * Hospital类的mapper，继承自MPJBaseMapper
 */
public interface HospitalMapper extends MPJBaseMapper<Hospital> {
}
