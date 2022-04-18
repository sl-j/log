package com.lei.controller;


import com.lei.domain.ResponseResult;
import com.lei.domain.entity.User;
import com.lei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 获取个人信息
     * @return
     */
    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }


    /**
     * 更新个人信息
     * @param user
     * @return
     */
    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    /**
     * 用户注册功能
     *
     */
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }
}
