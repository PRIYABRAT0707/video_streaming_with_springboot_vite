package com.lernercurve.course.util.deadlocksituationtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lernercurve.course.entity.VideoMetadata;
import com.lernercurve.course.repository.VideoMetadataRepository;
import com.lernercurve.course.util.deadlocksituation.DeadlockWithCompFuture;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadlockWithCompFutureTest {

	@Mock
	private VideoMetadataRepository videoMetadataRepository;

	@InjectMocks
	private DeadlockWithCompFuture deadlockWithCompFuture;

	private ReentrantLock lock;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		lock = new ReentrantLock();
	}

	@Test
	public void testGetVideoMetaData_LockAcquired() {
		log.info("inside testGetVideoMetaData_LockAcquired:- ");
		List<VideoMetadata> mockMetaData = new ArrayList<>();
		mockMetaData.add(new VideoMetadata()); 
		when(videoMetadataRepository.findAll()).thenReturn(mockMetaData);
		CompletableFuture<List<VideoMetadata>> future = this.deadlockWithCompFuture.getVideoMetaData(lock);
		List<VideoMetadata> result = future.join(); 
		assertNotNull(result);
		assertEquals(1, result.size());
		verify(videoMetadataRepository).findAll();
	}

	@Test
	public void testGetVideoMetaData_LockNotAcquired() throws InterruptedException {
		log.info("inside testGetVideoMetaData_LockNotAcquired:- ");
		lock.lock(); 
		CompletableFuture<List<VideoMetadata>> future =  this.deadlockWithCompFuture.getVideoMetaData(lock);
		List<VideoMetadata> result = future.join();
		assertNotNull(result);
		assertTrue(result.isEmpty()); 
		verify(videoMetadataRepository, never()).findAll();
		lock.unlock();
	}

	@Test
	public void testGetVideoMetaData_ExceptionHandling() {
		log.info("inside testGetVideoMetaData_ExceptionHandling:- ");
		when(videoMetadataRepository.findAll()).thenThrow(new RuntimeException("Database error"));
		CompletableFuture<List<VideoMetadata>> future = this.deadlockWithCompFuture.getVideoMetaData(lock);
		List<VideoMetadata> result = future.join(); 
		assertNotNull(result);
		assertTrue(result.isEmpty()); 
		verify(videoMetadataRepository).findAll();
		
	}

}
