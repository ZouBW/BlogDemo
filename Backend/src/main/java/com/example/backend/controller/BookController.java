package com.example.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.Book;
import com.example.backend.service.BookService;
import com.example.backend.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    BookService bookService;

    @PostMapping
    public Result<?> save(@RequestBody Book book) {
        int ret = bookService.saveBook(book);

        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Book book) {
        int ret = bookService.updateById(book);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        int ret = bookService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> get(@PathVariable Long id) {
        Book book = bookService.getById(id);
        if (book == null) {
            return Result.error("-1", "查询失败");
        } else {
            return Result.success(book);
        }
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String keyword) {
        Page<Book> bookPage = bookService.findPage(pageNum, pageSize, keyword);
        return Result.success(bookPage);
    }
}
