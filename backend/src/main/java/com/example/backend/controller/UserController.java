package com.example.backend.controller;

import com.example.backend.dto.UserLoginFormDTO;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.utils.Result;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginFormDTO userLoginFormDTO) throws Exception {
        return userService.getUserByUserIdAndPassword(userLoginFormDTO);
    }

    @RequestMapping("/isExists")
    public Result isExists(@RequestBody @NotNull User user) {
        return userService.getUserByUserId(user.getUserId());
    }

    @RequestMapping("/register")
    public Result register(@RequestBody User user) throws Exception {
        return userService.saveUser(user);
    }
}
