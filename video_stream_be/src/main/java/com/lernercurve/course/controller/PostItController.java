package com.lernercurve.course.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lernercurve.course.dto.VideoMetadataDto;
import com.lernercurve.course.dto.VideoUploadLocationDto;
import com.lernercurve.course.service.LocationService;
import com.lernercurve.course.service.VideoUploadService;

@RestController
public class PostItController {

    @Autowired
    private LocationService locationService;
    @Autowired
    private VideoUploadService videoUploadService;

    @PostMapping("/save-location")
    public ResponseEntity<Map<String, Object>> saveUloadedLocation(
            @RequestBody VideoUploadLocationDto videoUploadLocationDto) {
        return ResponseEntity.ok(this.locationService.saveLocation(videoUploadLocationDto));
    }

    @PostMapping("/save-video")
    public ResponseEntity<Map<String, Object>> saveVideo(
            @ModelAttribute VideoMetadataDto videoMetadataDto) {
        return ResponseEntity.ok(this.videoUploadService.saveVideo(videoMetadataDto));
    }
}
