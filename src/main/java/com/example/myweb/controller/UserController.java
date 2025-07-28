package com.example.myweb.controller;

import com.example.myweb.pojo.Result;
import com.example.myweb.pojo.User;
import com.example.myweb.service.UserService;
import com.example.myweb.utils.JwtUtil;
import com.example.myweb.utils.ShaUtil;
import com.example.myweb.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //登入
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$")String password){
        User loginUser = userService.findByUserName(username);
        log.info("登入的使用者資料: {}",loginUser);
        if(loginUser == null){
            log.info("用戶不存在");
            return Result.error("此用戶不存在");
        }

        if(ShaUtil.getSHA256(password).equals(loginUser.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);

            //token存入Redis
            ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,12, TimeUnit.HOURS);

            log.info("登入成功");
            return Result.success(token);
        }

        log.info("密碼錯誤");
        return Result.error("密碼錯誤");
    }

    //註冊
    @PostMapping("/register")
    public Result<String> register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        log.info("傳入的值:{} , {}",username,password);
        User u = userService.findByUserName(username);
        if(u == null){
            log.info("無此使用者 進入註冊");
            userService.register(username,password);
            return Result.success();
        }else{
            return Result.error("重複的使用者名稱");
        }
    }

    //取得使用者資料
    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token){
//        Map<String, Object> map = JwtUtil.parseToken(token);
//        log.info("claims= {}",map);
//        String username = (String)map.get("username");
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    //更新使用者資料
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    //更新照片
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    //更新密碼
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token){
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的參數");
        }

        Map<String,Object> map = ThreadLocalUtil.get();
        User loginUser = userService.findByUserName((String) map.get("username"));
        if(!loginUser.getPassword().equals(ShaUtil.getSHA256(oldPwd))){
            return Result.error("原密碼不正確");
        }

        if (!rePwd.equals(newPwd)) {
            return Result.error("填寫的新密碼不一致");
        }

        userService.updatePwd(newPwd);

        //更換密碼成功後 移除舊的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);

        return Result.success();
    }
}
