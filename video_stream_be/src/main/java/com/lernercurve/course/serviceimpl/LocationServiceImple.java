package com.lernercurve.course.serviceimpl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lernercurve.course.dto.VideoUploadLocationDto;
import com.lernercurve.course.entity.VideoUploadLocation;
import com.lernercurve.course.repository.VideoUploadLocationRepository;
import com.lernercurve.course.service.LocationService;

@Service
public class LocationServiceImple implements LocationService {

    @Autowired
    private VideoUploadLocationRepository videoUploadLocationRepository;

    @Override
    public Map<String, Object> saveLocation(VideoUploadLocationDto videoUploadLocationDto) {
        Map<String, Object> responseMap = new HashMap<>();

        var videoUploadLocation = new VideoUploadLocation();
        videoUploadLocation.setAddress(videoUploadLocationDto.getAddress());
        videoUploadLocation.setUploadedDateTime(LocalDateTime.now());
        videoUploadLocation.setUsername(videoUploadLocationDto.getUsername());
        videoUploadLocation.setCity(videoUploadLocationDto.getCity());
        videoUploadLocation.setState(videoUploadLocationDto.getState());
        var savedData = this.videoUploadLocationRepository.save(videoUploadLocation);
        responseMap.put("response", savedData);
        return responseMap;
    }

}
