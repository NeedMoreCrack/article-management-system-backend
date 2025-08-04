package com.example.myweb.service;

import com.example.myweb.pojo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    //封面照片上傳
    Result upload(MultipartFile file) throws Exception;

    //頭像上傳  不更新資料庫
    Result uploadAvatar(MultipartFile file) throws Exception;

    //照片上傳後 確認才更新資料庫
    void updateUserPic(String url);
}
