package com.example.myweb.service;

import com.example.myweb.pojo.Category;

import java.util.List;

public interface CategoryService {
    //新增分類
    void add(Category category);

    //列表查詢
    List<Category> list();

    //以id查詢分類
    Category findById(Integer id);

    //文章更新分類
    void update(Category category);

    //以id刪除文章分類
    void delete(Integer id);
}
