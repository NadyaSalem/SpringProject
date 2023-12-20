package com.onlinetherapy.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.UUID;

@Service
public class ImageCloudService {
    private final Cloudinary cloudinary;

    public ImageCloudService() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dmowldibf",
                "api_key", "969821936182642",
                "api_secret", "C43_L0AnGwcy3wVIdovwq3bgyjA",
                "secure", true));
    }

    public String saveImage(MultipartFile multipartFile) {
        //използваме рандъм за да подсигурим уникалност на имената на снимките
        String imageId = UUID.randomUUID().toString();

        Map params = ObjectUtils.asMap(
                "public_id", imageId,
                "overwrite", true,
                "resource_type", "image"
        );

        File tmpFile = new File(imageId);
        try {
            Files.write(tmpFile.toPath(), multipartFile.getBytes());
            cloudinary.uploader().upload(tmpFile, params);
            Files.delete(tmpFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return String.format("https://res.cloudinary.com/dmowldibf/image/upload/v1678104409/" +
                imageId + "." + getFileExtension(multipartFile.getOriginalFilename()));
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.') + 1);
    }
}
