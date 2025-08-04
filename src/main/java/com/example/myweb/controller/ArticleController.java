package com.example.myweb.controller;

import com.example.myweb.pojo.Article;
import com.example.myweb.pojo.PageBean;
import com.example.myweb.pojo.Result;
import com.example.myweb.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //新增文章
    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        log.info("新增文章");
        articleService.add(article);
        log.info("新增文章成功");
        return Result.success();
    }

    //取得文章列表
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        log.info("參數: {}, {}, {}, {}",pageNum,pageSize,categoryId,state);
        log.info("取得文章列表中...");
        PageBean<Article> pb = articleService.list(pageNum,pageSize,categoryId,state);
        log.info("查詢成功");
        log.info("回傳文章列表: {}",pb);
        return Result.success(pb);
    }

    //修改文章
    @PutMapping
    public Result edit(@RequestBody @Validated Article article){
        log.info("修改文章");
        articleService.edit(article);
        log.info("修改文章成功");
        return Result.success();
    }

    //刪除文章
    @DeleteMapping
    public Result delete(@RequestParam Integer deleteId){
        log.info("傳入的刪除id: {}",deleteId);
        articleService.delete(deleteId);
        return Result.success();
    }
}
