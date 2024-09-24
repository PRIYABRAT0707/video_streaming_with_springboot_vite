package com.lernercurve.course.util.deadlocksituation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.lernercurve.course.entity.VideoMetadata;
import com.lernercurve.course.repository.VideoMetadataRepository;
import com.lernercurve.course.repository.VideoUploadLocationRepository;
import com.lernercurve.course.util.ExtraResponse;
import com.lernercurve.course.util.LernerUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DeadLockCreation {

	@Autowired
	private VideoMetadataRepository videoMetadataRepository;
	@Autowired
	private VideoUploadLocationRepository videoUploadLocationRepository;
	@Autowired
	private DeadlockWithCompFuture deadlockWithCompFuture;

	private ReentrantLock videoMetaDataLock = new ReentrantLock();
	private ReentrantLock videoUploadLocation = new ReentrantLock();
	private Condition videoMetaDataCondition = videoMetaDataLock.newCondition();
	private Condition videoUploadCondition = videoUploadLocation.newCondition();
	
	public Map<String, Object> getMetaCount(){
		ExecutorService executor=Executors.newFixedThreadPool(100);
		ReentrantLock lock=new ReentrantLock();
		List<VideoMetadata> list = Stream.iterate(1,n->n+1).limit(10).map(i->this.deadlockWithCompFuture.getVideoMetaData(lock)).map(CompletableFuture::join).flatMap(i->i.stream()).toList();
		
		return LernerUtil.responseStructure(HttpStatus.OK, HttpStatus.OK.value(), "Data Fetched successfully!!", list,new ExtraResponse<String>("ppanda","ppanda"),new ExtraResponse<String>("spanda","spanda"));
	}
	
	
	
	
	
	public List<Object> createingADeadlock() {
		CompletableFuture<List<Object>> VideoMetadataList = CompletableFuture.supplyAsync(() -> {
			List<Object> returnedList = new ArrayList<>();

			synchronized (this.videoMetadataRepository) {
				log.info("thread 1 holding videMetaData:- {}", Thread.currentThread());
				returnedList.add(this.videoMetadataRepository.findAll());
				synchronized (this.videoUploadLocationRepository) {
					log.info("thread 1 holding videoUploadLocation:- {}", Thread.currentThread());
					returnedList.add(this.videoUploadLocationRepository.findAll());
				}
			}

			return returnedList;
		});

		CompletableFuture<List<Object>> VideoUploadList = CompletableFuture.supplyAsync(() -> {
			List<Object> returnedList = new ArrayList<>();

			synchronized (this.videoUploadLocationRepository) {
				log.info("thread 2 holding videoUploadLocation:- {}", Thread.currentThread());
				returnedList.add(this.videoUploadLocationRepository.findAll());
				synchronized (this.videoMetadataRepository) {
					log.info("thread 2 holding videMetaData:- {}", Thread.currentThread());
					returnedList.add(this.videoMetadataRepository.findAll());
				}
			}

			return returnedList;
		});
		return Stream.of(VideoMetadataList, VideoUploadList).map(CompletableFuture::join)
				.flatMap(f -> f.parallelStream()).toList();
	}

//	public List<Object> overComeADeadlock(){return Collections.emptyList();}

	public List<Object> overComeADeadlock() {

		CompletableFuture<List<Object>> VideoMetadataList = CompletableFuture.supplyAsync(() -> {
			List<Object> returnedList = Collections.synchronizedList(new ArrayList<>());
			try {
				if (this.videoMetaDataLock.tryLock()) {
					try {
						log.info("thread 1 holding videMetaData:- {}", Thread.currentThread());
						returnedList.add(this.videoMetadataRepository.count());
						videoMetaDataCondition.signalAll();
						log.info("videoMetaDataCondition ended signalled success fully:- {}" ,videoUploadCondition);
						
						if (this.videoUploadLocation.tryLock()) {
							try {
								log.info("thread 1 holding videoUploadLocation:- {}", Thread.currentThread());
								returnedList.add(this.videoUploadLocationRepository.count());
							} catch (Exception e) {
								log.info("error occured while accuiring lock for videMetaData :- {}", e.getMessage());
							} finally {
								this.videoUploadLocation.unlock();
							}
						}
						else {
//							this.videoUploadLocation.lock();
							videoUploadCondition.await();
							try {
								log.info("waiting thread 1 holding videoUploadLocation in else :- {}", Thread.currentThread());
								returnedList.add(this.videoUploadLocationRepository.count());
							} finally {
								this.videoUploadLocation.unlock();
							}
						}

					} catch (Exception e) {
						Thread.currentThread().interrupt();
						log.info("error occured while accuiring lock for videMetaData :- {}", e);
					} finally {
						this.videoMetaDataLock.unlock();
					}

				}
			} catch (Exception e) {
				log.info("Thread interupted :- {}", e.getMessage());
			}

			return returnedList;
		});

		CompletableFuture<List<Object>> VideoUploadList = CompletableFuture.supplyAsync(() -> {
			List<Object> returnedList = Collections.synchronizedList(new ArrayList<>());
			try {
				if (this.videoUploadLocation.tryLock()) {
					try {
						log.info("thread 2 holding  videoUploadLocation:- {}", Thread.currentThread());
						returnedList.add(this.videoUploadLocationRepository.count());
						videoUploadCondition.signalAll();
						log.info("videoUploadLocation ended signalled success fully:- {}" ,videoUploadCondition);
						
						if (this.videoMetaDataLock.tryLock()) {
							try {
								log.info("thread 2 holding videMetaData:- {}", Thread.currentThread());
								returnedList.add(this.videoMetadataRepository.count());
							} catch (Exception e) {
								log.info("error occured while accuiring lock for videMetaData :- {}", e.getMessage());
							} finally {
								this.videoMetaDataLock.unlock();
							}
						}
						else {
//							videoMetaDataLock.lock();
							videoMetaDataCondition.await();
							try {
								log.info("waiting thread 2 holding videMetaData else block:- {}", Thread.currentThread());
								returnedList.add(this.videoMetadataRepository.count());
							} finally {
								this.videoMetaDataLock.unlock();
							}
						}

					} catch (Exception e) {
						Thread.currentThread().interrupt();
						log.info("error occured while accuiring lock for videoUploadLocation :- {}", e);
					} finally {
						this.videoUploadLocation.unlock();
					}
				}

			} catch (Exception e) {
				log.info("Thread interupted :- {}", e.getMessage());
			}

			return returnedList;
		});

		return Stream.of(VideoMetadataList, VideoUploadList).map(CompletableFuture::join)
				.flatMap(f -> f.parallelStream()).toList();
	}


}
