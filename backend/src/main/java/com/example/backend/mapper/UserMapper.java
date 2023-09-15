package com.example.backend.mapper;

import com.example.backend.entity.User;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * User类的mapper，继承自MPJBaseMapper
 */
public interface UserMapper extends MPJBaseMapper<User> {
}
