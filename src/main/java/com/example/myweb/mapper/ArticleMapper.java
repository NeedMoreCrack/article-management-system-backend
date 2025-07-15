package com.example.myweb.mapper;

import com.example.myweb.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增文章
    @Insert("insert into article(title, content, cover_img, category_id, create_user, create_time, update_time)" +
            " values (#{title},#{content},#{coverImg},#{categoryId},#{createUser},now(),now())")
    void add(Article article);

    //分頁查詢
    List<Article> list(Integer userId, Integer categoryId, String state);
}
