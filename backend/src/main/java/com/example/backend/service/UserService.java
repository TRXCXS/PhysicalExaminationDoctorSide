package com.example.backend.service;

import com.example.backend.dto.UserLoginFormDTO;
import com.example.backend.entity.User;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends MPJBaseService<User> {
    Result getUserByUserIdAndPassword(UserLoginFormDTO userLoginFormDTO) throws Exception;

    Result getUserByUserId(String userId);

    Result saveUser(User user) throws Exception;
}
