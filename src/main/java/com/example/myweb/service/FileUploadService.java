package com.example.myweb.service;

import com.example.myweb.pojo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    //圖片上傳
    Result upload(MultipartFile file) throws Exception;
}
