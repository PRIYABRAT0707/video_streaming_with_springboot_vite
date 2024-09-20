package com.lernercurve.course.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoMetadataDto {

    private String uploadedBy;
    private String filename;
    private String filePath;
    private String fileType;
    private long fileSize;
    private LocalDateTime uploadDate;
    private LocalDateTime lastModifiedDate;
    private String videoTitle;
    private String description;
    private String genre;
    private int duration;
    private int width;
    private int height;
    private String resolution;
    private String codec;
    private String aspectRatio;
    private String language;
    private boolean isHD;
    private String uploader;
    private String tags;
    private String thumbnailUrl;
    private String location;
    private long viewCount;
    private double rating;
    private MultipartFile multipartFile;
}
