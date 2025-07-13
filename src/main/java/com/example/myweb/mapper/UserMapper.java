package com.example.myweb.mapper;

import com.example.myweb.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    //查詢當前使用者
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    //新增使用者
    @Insert("insert into user (username,password,create_time,update_time) " +
            " values (#{username},#{password},now(),now())")
    void add(String username, String password);

    //更新使用者資料
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    //更新使用者照片
    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl,Integer id);

    //更新密碼
    @Update("update user set password=#{newPwd},update_time=now() where id=#{id}")
    void updatePwd(String newPwd, Integer id);
}
