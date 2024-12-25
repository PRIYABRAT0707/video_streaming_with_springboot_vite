package com.lernercurve.course.serviceimpl;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import com.lernercurve.course.helpers.CompletableFutureType;
import com.lernercurve.course.service.RandomResponses;

@Component
public class RandomResponsesImpl implements RandomResponses {

	@Override
	public CompletableFuture<CompletableFutureType<?, ?>> getResponse() {
		
		return CompletableFuture.supplyAsync(()->{
			CompletableFutureType<String, Exception> response =new CompletableFutureType<String, Exception>();
			try {
				response.setResponseType("ppanda");
				
			} catch (Exception e) {
				response.setErrorType(e);
			}
			return response;
		});
	}

}
