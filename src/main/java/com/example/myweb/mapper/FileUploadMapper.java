package com.example.myweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FileUploadMapper {
    @Update("update article set cover_img=#{imgURL} where create_user=#{userId}")
    void insertUploadURL(Integer userId,String imgURL);
}
