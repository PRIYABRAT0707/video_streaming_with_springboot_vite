package com.lernercurve.course.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lernercurve.course.dto.VideoMetadataDto;
import com.lernercurve.course.dto.VideoUploadLocationDto;
import com.lernercurve.course.entity.VideoMetadata;
import com.lernercurve.course.exception.exceptions.MultipartFileCanNotBeNull;
import com.lernercurve.course.service.LocationService;
import com.lernercurve.course.service.VideoUploadService;
import com.lernercurve.course.util.deadlocksituation.DeadLockCreation;

@RestController
@RequestMapping("/videos")
public class PostItController {

    @Autowired
    private LocationService locationService;
    @Autowired
    private VideoUploadService videoUploadService;
    @Autowired
    private DeadLockCreation deadLockCreation;

    @PostMapping("/save-location")
    public ResponseEntity<Map<String, Object>> saveUloadedLocation(
            @RequestBody VideoUploadLocationDto videoUploadLocationDto) {
        return ResponseEntity.ok(this.locationService.saveLocation(videoUploadLocationDto));
    }

    @PostMapping("/save-video")
    public ResponseEntity<Map<String, Object>> saveVideo(
            @ModelAttribute VideoMetadataDto videoMetadataDto) throws MultipartFileCanNotBeNull {
        return ResponseEntity.ok(this.videoUploadService.saveVideo(videoMetadataDto));
    }
    
    @GetMapping("/get-video")
    public ResponseEntity<List<Object>> getVideo()  {
        return ResponseEntity.ok(this.deadLockCreation.createingADeadlock());
    }
    
    @GetMapping("/get-video-by-thread")
    public ResponseEntity<List<Object>> getVideoByThread()  {
        return ResponseEntity.ok(this.deadLockCreation.overComeADeadlock());
    }
    
    @GetMapping("/get-database-by-thread")
    public ResponseEntity<Map<String, Object>> getDataBaseCount()  {
        return ResponseEntity.ok(this.deadLockCreation.getMetaCount());
    }
}
