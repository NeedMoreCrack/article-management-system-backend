package com.example.myweb.service.impl;

import com.example.myweb.mapper.CategoryMapper;
import com.example.myweb.pojo.Category;
import com.example.myweb.service.CategoryService;
import com.example.myweb.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    //新增文章分類
    @Override
    public void add(Category category) {
        log.info("新增文章分類");
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        category.setCreateUser(userId);
        categoryMapper.add(category);
    }

    //取得列表
    @Override
    public List<Category> list() {
        log.info("取得列表中...");
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        log.info("取得列表: {}",categoryMapper.list(userId));
        return categoryMapper.list(userId);
    }

    //以id查詢分類
    @Override
    public Category findById(Integer id) {
        Category c = categoryMapper.findById(id);
        return c;
    }

    //文章更新分類
    @Override
    public void update(Category category) {
        log.info("更新文章分類");
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
