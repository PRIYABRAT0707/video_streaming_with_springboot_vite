package com.lernercurve.course.service;

import java.util.Map;

import com.lernercurve.course.dto.VideoMetadataDto;

public interface VideoUploadService {
    Map<String, Object> saveVideo(VideoMetadataDto videoMetadataDto);
}
