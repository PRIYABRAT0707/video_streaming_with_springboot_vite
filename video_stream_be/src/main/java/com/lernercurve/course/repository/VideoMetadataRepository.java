package com.lernercurve.course.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lernercurve.course.entity.VideoMetadata;

@Repository
public interface VideoMetadataRepository extends MongoRepository<VideoMetadata, String> {

}
