package com.example.myweb.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import com.example.myweb.mapper.FileUploadMapper;
import com.example.myweb.pojo.Result;
import com.example.myweb.service.FileUploadService;
import com.example.myweb.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {
    //Cloudinary image upload API
    @Value("${CLOUDINARY_URL}")
    String CLOUDINARY_URL;

    @Autowired
    private FileUploadMapper fileUploadMapper;

    //圖片上傳
    @Override
    public Result upload(MultipartFile file) throws Exception {
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

        //圖片上傳網址
        String imgURL = (String) uploadResult.get("secure_url");
        Map<String,Object> map  = ThreadLocalUtil.get();
        Integer userId =  (Integer)map.get("id");
        fileUploadMapper.insertUploadURL(userId,imgURL);

        ApiResponse details = cloudinary.api().resource(publicId, ObjectUtils.asMap(
                "type", "authenticated",
                "quality_analysis", true
        ));

        log.info("上傳結果: {}", uploadResult);
        log.info("資源細節: {}", details);
        return Result.success();
    }
}
