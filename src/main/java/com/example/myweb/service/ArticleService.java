package com.example.myweb.service;

import com.example.myweb.pojo.Article;
import com.example.myweb.pojo.PageBean;

public interface ArticleService {
    //新增文章
    void add(Article article);

    //條件分業查詢
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    //修改文章
    void edit(Article article);

    //依id刪除文章
    void delete(Integer deleteId);
}
