package com.lernercurve.course.serviceimpl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.lernercurve.course.dto.VideoMetadataDto;
import com.lernercurve.course.entity.VideoMetadata;
import com.lernercurve.course.exception.exceptions.MultipartFileCanNotBeNull;
import com.lernercurve.course.repository.VideoMetadataRepository;
import com.lernercurve.course.service.VideoUploadService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VideoUploadServiceImpl implements VideoUploadService {

	@Autowired
	private VideoMetadataRepository videoMetadataRepository;

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
			log.info("error occured while creating path... {} ", e.getMessage());
		}

	}

	@Override
	public Map<String, Object> saveVideo(VideoMetadataDto videoMetadataDto) throws MultipartFileCanNotBeNull {
		 Map<String, Object> responseMap = new HashMap<>();
		 
		if (videoMetadataDto.getMultipartFile() == null || videoMetadataDto.getMultipartFile().isEmpty()) {
			throw new MultipartFileCanNotBeNull();
		}
		var videoMetadata = new VideoMetadata();
		videoMetadata.setFilename(videoMetadataDto.getMultipartFile().getOriginalFilename());
		videoMetadata.setLastModifiedDate(LocalDateTime.now());
		videoMetadata.setDescription(videoMetadataDto.getDescription());
		videoMetadata.setUploadedBy(videoMetadataDto.getUploadedBy());
		VideoMetadata save = this.videoMetadataRepository.save(videoMetadata);
		 responseMap.put("response", save);
		return responseMap;
	}
}