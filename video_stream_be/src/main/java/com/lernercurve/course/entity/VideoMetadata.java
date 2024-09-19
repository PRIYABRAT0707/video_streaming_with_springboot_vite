package com.lernercurve.course.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class VideoMetadata {
    @Id
    private String videoId;
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
}
