package com.lernercurve.course.service;

import java.util.concurrent.CompletableFuture;

import com.lernercurve.course.helpers.CompletableFutureType;

public interface RandomResponses {
	CompletableFuture<CompletableFutureType<?, ?>> getResponse();

}
