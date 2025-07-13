package com.example.myweb.controller;

import com.example.myweb.pojo.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {
    //取得所有文章列表
    @GetMapping("/list")
    public Result<String> list(@RequestHeader(name = "Authorization") String token, HttpServletResponse response){
//        驗證用LoginInterceptor
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//            return Result.success("所有文章");
//        } catch (Exception e) {
//            response.setStatus(401);
//            return Result.error("未登入");
//        }
        return Result.success("所有文章列表");
    }
}
