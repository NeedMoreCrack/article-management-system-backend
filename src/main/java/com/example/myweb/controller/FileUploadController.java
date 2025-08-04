package com.example.myweb.controller;

import com.example.myweb.pojo.Result;
import com.example.myweb.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@RestController
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    //圖片上傳
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        log.info("封面圖片上傳...");
        Result<String> imgUrl = fileUploadService.upload(file);
        return Result.success(imgUrl.getData());
    }

    //頭像上傳 不寫入資料庫 只回傳網址
    @PostMapping("/upload/avatar")
    public Result<String> uploadAvatar(@RequestParam("file") MultipartFile file) throws Exception {
        log.info("使用者照片上傳中...");
        Result<String> imgUrl = fileUploadService.uploadAvatar(file);
        return Result.success(imgUrl.getData());
    }

    //頭像上傳  更新資料庫
    @PutMapping("/updateAvatar")
    public Result<String> updateSQL(@RequestBody Map<String, String> params) {
        String url = params.get("url");
        log.info("更新user_pic...傳入的值: {}",url);
        fileUploadService.updateUserPic(url);
        log.info("更新成功");
        return Result.success();
    }
}
