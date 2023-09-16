package com.example.backend.controller;

import com.example.backend.dto.UserLoginFormDTO;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.utils.Result;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 用户登录接口
     *
     * @param userLoginFormDTO：用户登录表单DTO
     * @return 返回Result封装查找结果，成功返回用户对象，失败返回null
     * @throws Exception
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginFormDTO userLoginFormDTO) throws Exception {
        return userService.getUserByUserIdAndPassword(userLoginFormDTO);
    }

    /**
     * 判断用户是否存在的接口
     *
     * @param user：传入的用户对象
     * @return 返回Result封装查找结果，成功返回用户对象，失败返回errorMessage
     */
    @RequestMapping("/isExists")
    public Result isExists(@RequestBody @NotNull User user) {
        return userService.getUserByUserId(user.getUserId());
    }

    /**
     * 用户注册接口
     *
     * @param user：传入的用户对象
     * @return 返回Result封装注册结果，表示是否注册成功
     * @throws Exception
     */
    @RequestMapping("/register")
    public Result register(@RequestBody User user) throws Exception {
        return userService.saveUser(user);
    }

    /**
     * 根据姓名字段获取姓名列表供用户选择
     *
     * @param partialName：姓名字段
     * @return 返回的模糊查询到的姓名
     */
    @RequestMapping("/getUserNamesByFuzzyQuery")
    public List<String> getUserNamesByFuzzyQuery(@RequestParam String partialName) {
        return userService.getUserNamesByFuzzyQuery(partialName);
    }
}
