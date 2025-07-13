package com.example.myweb.service;

import com.example.myweb.pojo.User;

public interface UserService {
    //以使用者名稱查找
    User findByUserName(String username);

    //註冊
    void register(String username,String password);

    //更新
    void update(User user);

    //更新頭像
    void updateAvatar(String avatarUrl);

    //更新密碼
    void updatePwd(String newPwd);
}
