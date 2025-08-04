package com.example.myweb.service.impl;

import com.example.myweb.mapper.ArticleMapper;
import com.example.myweb.pojo.Article;
import com.example.myweb.pojo.PageBean;
import com.example.myweb.service.ArticleService;
import com.example.myweb.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    //新增文章
    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);
    }

    //分頁查詢
    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pb = new PageBean<>();

        //開啟分頁查詢PageHelper
        PageHelper.startPage(pageNum,pageSize);

        //調用Mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer)map.get("id");
        List<Article> am =  articleMapper.list(userId,categoryId,state);
        //Page提供方法，可以取得PageHelper分頁查詢後得到的總記錄和當前頁面資料
        Page<Article> p = (Page<Article>) am;

        //將資料加入PageBean中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    //修改文章
    @Override
    public void edit(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        log.info("取得ID: {}",article.getId());
        log.info("取得標題: {}",article.getTitle());
        log.info("取得內容: {}",article.getContent());
        log.info("取得發佈狀態: {}",article.getState());
        log.info("取得封面圖URL: {}",article.getCoverImg());
        articleMapper.edit(article);
    }

    //依id刪除文章
    @Override
    public void delete(Integer deleteId) {
        articleMapper.delete(deleteId);
    }
}
