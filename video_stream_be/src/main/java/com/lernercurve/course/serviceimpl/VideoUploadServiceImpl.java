package com.lernercurve.course.serviceimpl;

import java.io.File;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.lernercurve.course.dto.VideoMetadataDto;
import com.lernercurve.course.service.VideoUploadService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VideoUploadServiceImpl implements VideoUploadService {

    @Value("${videoDirectory.path}")
    public String videoPath;

    @PostConstruct()
    public void init() {
        try {
            File folder = new File(videoPath);
            if (!folder.exists()) {
                boolean iscreated = folder.mkdirs();
                if (iscreated) {
                    log.info("Folder created successfully.");
                } else {
                    log.info("unable to create folder..");
                }
            } else {
                log.info("Folder already exists..");
            }

        } catch (Exception e) {
            log.info("error occured while creating path... ", e);
        }

    }

    @Override
    public Map<String, Object> saveVideo(VideoMetadataDto videoMetadataDto) {
       if (videoMetadataDto.getMultipartFile() !==null) {
        
       }
       throw new 
    }
}