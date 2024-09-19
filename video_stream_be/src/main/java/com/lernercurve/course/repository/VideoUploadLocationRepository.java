package com.lernercurve.course.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lernercurve.course.entity.VideoUploadLocation;

@Repository
public interface VideoUploadLocationRepository extends MongoRepository<VideoUploadLocation, String> {

}
