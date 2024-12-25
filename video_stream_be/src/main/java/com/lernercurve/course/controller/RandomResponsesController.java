package com.lernercurve.course.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lernercurve.course.helpers.CompletableFutureType;
import com.lernercurve.course.service.RandomResponses;

@RestController
public class RandomResponsesController {

	@Autowired
	private RandomResponses randomResponses;

	@GetMapping("/comp-one")
	CompletableFuture<CompletableFutureType<?, ?>> getResponse() {
		return this.randomResponses.getResponse();
	}
}
