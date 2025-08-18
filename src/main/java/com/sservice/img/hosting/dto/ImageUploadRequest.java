package com.sservice.img.hosting.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadRequest {
    private MultipartFile file;
    private String url;
    private String title;
    private String description;
    private boolean isAdult;
}
