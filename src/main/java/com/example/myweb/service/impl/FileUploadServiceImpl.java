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

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {
    //Cloudinary image upload API
    @Value("${CLOUDINARY_URL}")
    String CLOUDINARY_URL;

    @Autowired
    private FileUploadMapper fileUploadMapper;

    //封面圖片上傳 只回傳網址 不寫入資料庫
    @Override
    public Result<String> upload(MultipartFile file) throws Exception {
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
//        Map<String,Object> map = ThreadLocalUtil.get();
//        Integer userId =  (Integer)map.get("id");
//        LocalDateTime now = LocalDateTime.now();
//        fileUploadMapper.insertUploadURL(userId,imgURL,now);

        ApiResponse details = cloudinary.api().resource(publicId, ObjectUtils.asMap(
                "type", "authenticated",
                "quality_analysis", true
        ));

        log.info("封面上傳結果: {}", uploadResult);
        log.info("封面資源細節: {}", details);
        return Result.success(imgURL);
    }

    //頭像上傳  不更新資料庫
    @Override
    public Result uploadAvatar(MultipartFile file) throws Exception {
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

        //圖片上傳網址  上傳分兩步  1.上傳  2.按下更換才會寫入資料庫  這邊只回傳上傳網址
        String imgURL = (String) uploadResult.get("secure_url");
//        Map<String,Object> map = ThreadLocalUtil.get();
//        Integer userId =  (Integer)map.get("id");
//        LocalDateTime now = LocalDateTime.now();
//        fileUploadMapper.insertUploadAvatarURL(userId,imgURL,now);

        ApiResponse details = cloudinary.api().resource(publicId, ObjectUtils.asMap(
                "type", "authenticated",
                "quality_analysis", true
        ));

        log.info("使用者照片上傳結果: {}", uploadResult);
        log.info("使用者照片資源細節: {}", details);
        return Result.success(imgURL);
    }

    //照片上傳後 確認才更新資料庫
    @Override
    public void updateUserPic(String url) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId =  (Integer)map.get("id");
        LocalDateTime now = LocalDateTime.now();
        fileUploadMapper.insertUploadAvatarURL(userId,url,now);
    }
}
