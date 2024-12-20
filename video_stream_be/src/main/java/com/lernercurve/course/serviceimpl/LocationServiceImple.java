package com.lernercurve.course.serviceimpl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lernercurve.course.dto.VideoUploadLocationDto;
import com.lernercurve.course.entity.VideoUploadLocation;
import com.lernercurve.course.repository.VideoUploadLocationRepository;
import com.lernercurve.course.service.LocationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
    
    public static void main(String[] args) {
        // Example list of CompletableFuture objects
        List<CompletableFuture<Void>> futureList = List.of(
            CompletableFuture.runAsync(() -> {
            	log.info("current thread:- {}",Thread.currentThread());
            	try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	log.info("Task 1");
//            	return "Task 1";
            }),
            CompletableFuture.runAsync(() -> {
            	log.info("current thread:- {}",Thread.currentThread());
            	log.info("Task 2");
//            	return "Task 2";
            }),
            CompletableFuture.runAsync(() -> {
            	log.info("current thread:- {}",Thread.currentThread());
            	log.info("Task 3");
//            	return "Task 3";
            })
        );

        // Convert List<CompletableFuture<String>> to CompletableFuture<String>[]
       CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();
      
    }

}
