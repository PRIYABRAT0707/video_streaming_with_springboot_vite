package com.lernercurve.course.util.deadlocksituation;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Phasers {
	 private static final ExecutorService executor = Executors.newFixedThreadPool(5);
	 public static CompletableFuture<Void> processLoanStage(Phaser phaser, String stage, int duration) {
	        return CompletableFuture.runAsync(() -> {
	            phaser.register(); // Ensure the task is properly registered
	            try {
	                log.info("{} started", stage);
	                TimeUnit.SECONDS.sleep(duration);

	                log.info("Before Waiting - {} - Phase: {}, Registered: {}, Arrived: {}",
	                        stage, phaser.getPhase(), phaser.getRegisteredParties(), phaser.getArrivedParties());

	                phaser.arriveAndAwaitAdvance(); // Wait for all tasks to reach this point

	                log.info("After Waiting - {} - Phase: {}, Registered: {}, Arrived: {}",
	                        stage, phaser.getPhase(), phaser.getRegisteredParties(), phaser.getArrivedParties());

	                log.info("{} completed", stage);
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            } finally {
	                phaser.arriveAndDeregister(); // Deregister after completion
	            }
	        }, executor);
	    }
	
	public static void main(String[] args) {
		 Phaser phaser = new Phaser(1); // Register main thread

	        // Start async tasks
	        CompletableFuture<Void> task1 = processLoanStage(phaser, "Document Verification", 10);
	        CompletableFuture<Void> task2 = processLoanStage(phaser, "Credit Score Check", 20);
	        CompletableFuture<Void> task3 = processLoanStage(phaser, "Loan Approval", 7);

	        // Main thread should wait dynamically for all phases to complete
	        while (phaser.getRegisteredParties() > 1 || !task1.isDone() || !task2.isDone() || !task3.isDone()) { 
	            log.info("Main thread waiting for phase {} to complete...", phaser.getPhase());
	            phaser.arriveAndAwaitAdvance();
	        }

	        phaser.arriveAndDeregister(); // Deregister main thread
	        log.info("✅ Loan Processing Completed!");
	        executor.shutdown();
	}
	
}
