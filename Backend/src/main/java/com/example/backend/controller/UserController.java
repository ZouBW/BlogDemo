package com.example.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {

        return userService.register(user);
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        return userService.checkUser(user);
    }

    @PostMapping
    public Result<?> saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String keyword) {
        return userService.findPage(pageNum, pageSize, keyword);
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteById(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping
    public Result<?> updateId(@RequestBody User user) {
        return userService.updateById(user);
    }
}
