package com.example.myweb.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import com.example.myweb.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
public class FileUploadController {
    @Value("${CLOUDINARY_URL}")
    String CLOUDINARY_URL;
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);

        Map<String, Object> uploadParams = ObjectUtils.asMap(
                "use_filename", false,
                "unique_filename", true,
                "overwrite", false,
                "type", "authenticated",
                "resource_type", "auto", // 自動判斷 file 類型
                "filename", file.getOriginalFilename()
        );

        byte[] bytes = file.getBytes(); // 轉為 byte[]
        Map uploadResult = cloudinary.uploader().upload(bytes, uploadParams);

        String publicId = (String) uploadResult.get("public_id");

        ApiResponse details = cloudinary.api().resource(publicId, ObjectUtils.asMap(
                "type", "authenticated",
                "quality_analysis", true
        ));

        log.info("上傳結果: {}", uploadResult);
        log.info("資源細節: {}", details);

        return Result.success((String) uploadResult.get("secure_url"));
    }
}
