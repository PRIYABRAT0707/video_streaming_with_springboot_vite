package com.lernercurve.course.service;

import java.util.Map;

import com.lernercurve.course.dto.VideoUploadLocationDto;

public interface LocationService {

    Map<String, Object> saveLocation(VideoUploadLocationDto videoUploadLocationDto);

}
