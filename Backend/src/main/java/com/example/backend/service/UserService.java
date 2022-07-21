package com.example.backend.service;

import cn.hutool.core.util.StrUtil;
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

    public Result<?> saveUser(User user) {
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userMapper.insert(user);
        return Result.success();
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
}
