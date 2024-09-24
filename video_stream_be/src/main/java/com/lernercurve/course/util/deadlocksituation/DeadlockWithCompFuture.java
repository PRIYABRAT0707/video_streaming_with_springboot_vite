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
			List<List<VideoMetadata>> metaDataList = new ArrayList<>();
			try {
				log.info("running in current thread:- {}",Thread.currentThread(),lock.isHeldByCurrentThread());
				if (lock.tryLock()) {
					metaDataList.add(this.videoMetadataRepository.findAll());
				} else {
					log.info("others threads using this resourse videoMetadataRepository");
				}
			} catch (Exception e) {
				log.info("unable to accuire lock interupted:- ", e);
			} finally {
				lock.unlock();
			}
			return metaDataList;
		}).thenApplyAsync(result->result.stream().flatMap(i->i.stream()).toList());
	}

}
