package com.qzx.recruit.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author qzx
 * @create 2021-10-27 15:20
 * @function
 */
public class ImageUtils {
    public static String uploadImage(MultipartFile file) {
        String name = null;
        try {
            String accessKeySecret = "ffHtmlZqEXpITvWKrBKcy5RdY4eLCf";
            String accessKeyId = "LTAI4FeV3AP7KZiQFbVhzd9q";
            String endpoint = "https://oss-cn-beijing.aliyuncs.com";
            String bucketName = "hw-resume";

            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            String fileName = file.getOriginalFilename();
            assert fileName != null;
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            name = UUID.randomUUID() + suffixName;
            String completePath = "swiper/" + name;
            ossClient.putObject(bucketName, completePath, file.getInputStream());
            ossClient.shutdown();
            name = "https://hw-resume.oss-cn-beijing.aliyuncs.com/" + completePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }
}
