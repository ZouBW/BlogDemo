package com.example.backend.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.vo.Result;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserService {
    @Resource
    UserMapper userMapper;

    public Result<?> register(User user) {
        if (isUsernameExist(user.getUsername())) {
            return Result.error("-2", "用户名已存在");
        } else {
            insert(user);
            return Result.success();
        }
    }

    public Result<?> saveUser(User user) {
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        insert(user);
        return Result.success();
    }

    private void insert(User user) {
        userMapper.insert(user);
    }

    public Result<?> checkUser(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        User res = userMapper.selectOne(queryWrapper.eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword()));
        if (res != null) {
            return Result.success(res);
        } else {
            return Result.error("-1", "密码错误");
        }
    }

    public Result<?> findPage(Integer pageNum, Integer pageSize, String keyword) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper.like(StrUtil.isNotBlank(keyword), User::getNickName, keyword));


        return Result.success(userPage);
    }

    public Result<?> updateById(User user) {
        userMapper.updateById(user);
        return Result.success();
    }

    public Result<?> deleteById(Long id) {
        userMapper.deleteById(id);
        return Result.success();
    }

    public Result<?> getById(Long id) {
        return Result.success(userMapper.selectById(id));
    }

    private boolean isUsernameExist(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        User res = userMapper.selectOne(queryWrapper.eq(User::getUsername, username));
        if (res != null) {
            return true;
        } else {
            return false;
        }
    }
}
