package com.example.myweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@Mapper
public interface FileUploadMapper {
    //頭像URL更新
    @Update("update user set user_pic=#{userPic},update_time=#{now} where id=#{id}")
    void insertUploadAvatarURL(Integer id, String userPic, LocalDateTime now);
}
