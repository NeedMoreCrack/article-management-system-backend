package com.example.myweb.mapper;

import com.example.myweb.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //新增分類
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time) " +
            " values (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    //查詢所有列表
    @Select("select * from category where create_user = #{userId}")
    List<Category> list(Integer userId);

    //以id查詢分類
    @Select("select * from category where id = #{id}")
    Category findById(Integer id);
}
