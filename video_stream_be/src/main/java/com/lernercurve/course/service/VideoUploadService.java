package com.lernercurve.course.service;

import java.util.Map;

import com.lernercurve.course.dto.VideoMetadataDto;
import com.lernercurve.course.exception.exceptions.MultipartFileCanNotBeNull;

public interface VideoUploadService {
    Map<String, Object> saveVideo(VideoMetadataDto videoMetadataDto) throws MultipartFileCanNotBeNull;
}
