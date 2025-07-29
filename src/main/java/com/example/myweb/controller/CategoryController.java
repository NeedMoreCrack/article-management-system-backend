package com.example.myweb.controller;

import com.example.myweb.pojo.Category;
import com.example.myweb.pojo.Result;
import com.example.myweb.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //新增分類
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        log.info("新增分類");
        categoryService.add(category);
        return Result.success();
    }

    //列表查詢
    @GetMapping
    public Result<List<Category>> list(){
        log.info("列表查詢中...");
        List<Category> cs = categoryService.list();
        log.info("返回查詢結果 {}",cs);
        return Result.success(cs);
    }

    //以id查詢分類
    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        log.info("以ID查詢分類: {}",id);
        Category c = categoryService.findById(id);
        return Result.success(c);
    }

    //文章更新分類
    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        log.info("列表更新中...");
        categoryService.update(category);
        return Result.success();
    }
}
