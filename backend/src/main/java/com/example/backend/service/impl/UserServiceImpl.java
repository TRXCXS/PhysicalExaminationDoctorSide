package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.dto.UserLoginFormDTO;
import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.UserService;
import com.example.backend.utils.Result;
import com.example.backend.utils.SHA256;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;

    /**
     * 用户登录方法
     *
     * @param userLoginFormDTO：封装User对象登录表单的DTO
     * @return 返回值Result封装查找结果，找到则返回用户对象，否则返回的是null
     * @throws Exception
     */
    @Override
    public Result getUserByUserIdAndPassword(@NotNull UserLoginFormDTO userLoginFormDTO) throws Exception {
        Result result = getUserByUserId(userLoginFormDTO.getUserId());

        if (result.getIsSuccess() == Boolean.FALSE) {
            // 没有找到用户的情况
            return Result.fail("用户名或密码错误！");
        }

        User user = (User) result.getData();
        // 对输入的密码明文加密，然后与数据库的密文进行匹配
        String encryptedPassword = SHA256.encrypt(userLoginFormDTO.getPassword());

        boolean isMatch = user.getPassword().equals(encryptedPassword);
        if (!isMatch) {
            // 找到用户但密码错误的情况
            return Result.fail("用户名或密码错误！");
        }

        return result;
    }

    /**
     * 判断用户是否存在
     *
     * @param userId：User对象的Id
     * @return 返回值Result封装查找结果，找到则返回用户对象，否则返回的是null
     */
    @Override
    public Result getUserByUserId(String userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            return Result.fail("未找到指定userId的用户！");
        }

        return Result.success(user);
    }

    /**
     * 保存新的用户对象
     *
     * @param user：User对象
     * @return 返回值Result表示是否保存成功
     * @throws Exception
     */
    @Override
    @Transactional
    public Result saveUser(@NotNull User user) throws Exception {
        // 密码明文加密
        user.setPassword(SHA256.encrypt(user.getPassword()));
        int state = userMapper.insert(user);

        if (state == 0) {
            return Result.fail("插入失败！");
        } else {
            return Result.success();
        }
    }

    /**
     * 根据姓名字段获取姓名列表供用户选择
     *
     * @param partialName：模糊查询的姓名字段
     * @return 用户姓名列表
     */
    @Override
    public List<String> getUserNamesByFuzzyQuery(String partialName) {
        List<User> users = userMapper.selectList(
                new QueryWrapper<User>()
                        .like("realName", partialName)
        );

        List<String> userNameList = new ArrayList<>();
        for (User user : users) {
            userNameList.add(user.getRealName());
        }

        return userNameList;
    }
}
