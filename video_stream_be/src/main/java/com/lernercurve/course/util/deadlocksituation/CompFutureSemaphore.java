package com.lernercurve.course.util.deadlocksituation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lernercurve.course.repository.VideoMetadataRepository;
import com.lernercurve.course.repository.VideoUploadLocationRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CompFutureSemaphore {
	@Autowired
	private VideoUploadLocationRepository videoUploadLocationRepository;
	@Autowired
	private VideoMetadataRepository videoMetadataRepository;

	public String getMyFirstName() throws InterruptedException {
		Thread.sleep(2000);
		return "Priyabrat";
	}

	public String getMyFullName(String firstName) {
		return firstName + " Panda";
	}

	public CompletableFuture<String> customName(int taskId, ExecutorService service, Semaphore semaphore) {
		return CompletableFuture.supplyAsync(() -> {
			String myFullName = "";
			try {
				semaphore.acquire();
				log.info("current thread:- {}", Thread.currentThread());
				myFullName = this.getMyFullName(getMyFirstName());
				log.info("full name:- {}", myFullName);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} finally {
				semaphore.release();
			}
			return myFullName;
		}, service);
	}

	public List<String> compFUsingSemaphore() {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		final Semaphore compSemaphore = new Semaphore(10);
		var myList = Stream.iterate(1, n -> n + 1).limit(25)
				.map(item -> customName(item, executorService, compSemaphore)).toList().stream()
				.map(CompletableFuture::join).toList();
		executorService.shutdown();
		return myList;
	}

	public static void main(String[] args) {
		log.info("name list:- {}", new CompFutureSemaphore().compFUsingSemaphore());
	}

}
