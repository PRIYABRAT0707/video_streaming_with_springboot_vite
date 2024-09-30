package com.lernercurve.course.util.deadlocksituation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lernercurve.course.entity.VideoMetadata;
import com.lernercurve.course.repository.VideoMetadataRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DeadlockWithCompFuture {

	@Autowired
	private VideoMetadataRepository videoMetadataRepository;

	public CompletableFuture<List<VideoMetadata>> getVideoMetaData(ReentrantLock lock) {
		return CompletableFuture.supplyAsync(() -> {
			List<VideoMetadata> metaDataList = new ArrayList<>();
			boolean isLocked = false;
			try {
				log.info("Running in current thread: {}", Thread.currentThread().getName());
				if (lock.tryLock()) {
					isLocked = true;
					metaDataList = this.videoMetadataRepository.findAll();
				} else {
					log.info("Other threads are using the resource videoMetadataRepository");
				}
			} catch (Exception e) {
				log.error("Error while fetching video metadata: ", e.getMessage());
			} finally {
				log.info("lock accuired release:- {}",isLocked);
				if (isLocked) {
					lock.unlock();
				}
			}
			return metaDataList;
		});
	}

}
