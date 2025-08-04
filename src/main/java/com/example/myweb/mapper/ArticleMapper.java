package com.example.myweb.mapper;

import com.example.myweb.pojo.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增文章
    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time)" +
            " values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},now(),now())")
    void add(Article article);

    //分頁查詢
    List<Article> list(Integer userId, Integer categoryId, String state);

    //修改文章
    @Update("update article set title=#{title},cover_img=#{coverImg},content=#{content},state=#{state},update_time=#{updateTime} where id=#{id}")
    void edit(Article article);

    //依照id刪除文章
    @Delete("delete from article where id=#{id}")
    void delete(Integer deleteId);
}
