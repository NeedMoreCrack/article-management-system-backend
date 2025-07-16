package com.example.myweb.controller;

import com.example.myweb.pojo.Result;
import com.example.myweb.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
        fileUploadService.upload(file);
        return Result.success();
    }
}
