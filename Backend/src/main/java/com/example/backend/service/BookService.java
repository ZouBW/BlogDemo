package com.example.backend.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.Book;
import com.example.backend.entity.User;
import com.example.backend.mapper.BookMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BookService {
    @Resource
    BookMapper bookMapper;

    public int saveBook(Book book) {
        return bookMapper.insert(book);
    }

    public int updateById(Book book) {
        return bookMapper.updateById(book);
    }

    public int deleteById(Long id) {
        return bookMapper.deleteById(id);
    }

    public Book getById(Long id) {
        return bookMapper.selectById(id);
    }

    public Page<Book> findPage(Integer pageNum, Integer pageSize, String keyword) {
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        Page<Book> bookPage = bookMapper.selectPage(new Page<>(pageNum, pageSize),
                wrapper.like(StrUtil.isNotBlank(keyword), Book::getName, keyword));
        return bookPage;
    }

}
