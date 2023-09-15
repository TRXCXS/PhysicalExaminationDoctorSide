package com.example.backend.service;

import com.example.backend.dto.UserLoginFormDTO;
import com.example.backend.entity.User;
import com.example.backend.utils.Result;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public interface UserService extends MPJBaseService<User> {
    /**
     * @param userLoginFormDTO：封装User对象登录表单的DTO
     * @return 查询成功Result封装User对象，否则封装ErrorMessage
     * @throws Exception
     */
    Result getUserByUserIdAndPassword(UserLoginFormDTO userLoginFormDTO) throws Exception;

    /**
     * @param userId：User对象的Id
     * @return 查询成功Result封装User对象，否则封装ErrorMessage
     */
    Result getUserByUserId(String userId);

    /**
     * @param user：User对象
     * @return 返回对象Result的isSuccess属性表示保存User是否成功
     * @throws Exception
     */
    Result saveUser(User user) throws Exception;
}
