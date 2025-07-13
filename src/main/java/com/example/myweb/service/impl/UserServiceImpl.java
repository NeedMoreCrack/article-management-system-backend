package com.example.myweb.service.impl;

import com.example.myweb.mapper.UserMapper;
import com.example.myweb.pojo.User;
import com.example.myweb.service.UserService;
import com.example.myweb.utils.ShaUtil;
import com.example.myweb.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //查詢使用者
    @Override
    public User findByUserName(String username) {
        log.info("查詢使用者: {}",username);
        return userMapper.findByUserName(username);
    }

    //註冊
    @Override
    public void register(String username,String password){
        String sha256String = ShaUtil.getSHA256(password);
        userMapper.add(username,sha256String);
    }

    //更新
    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    //更新頭像
    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    //更新密碼
    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(ShaUtil.getSHA256(newPwd),id);
    }
}
