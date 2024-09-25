package com.lernercurve.course.util;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RedisTransactionManager {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public boolean setValue(String key, Object value) {
		boolean isDataSavedInRedis = false;
		try {
			this.redisTemplate.opsForValue().set(key, value);
			isDataSavedInRedis = true;
		} catch (Exception e) {
			log.info("unable to save given data in redis database:- {} ", e.getMessage());
		}
		return isDataSavedInRedis;
	}

	public Object getValue(String key) {
		Object retrivedValue = null;
		try {
			retrivedValue = this.redisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			log.info("unable to retrive saved data from redis database with key :- {} " + key, e.getMessage());
		}
		return retrivedValue;
	}

	public boolean deleteValue(String key) {
		boolean isDeleted = false;
		try {
			isDeleted = this.redisTemplate.delete(key);
			isDeleted = true;
		} catch (Exception e) {
			log.info("unable to delete saved data from redis database with key :- {} " + key, e.getMessage());
		}
		return isDeleted;
	}

	public boolean setValueWithExpiration(String key, Object value, long timeoutInMiliSeconds) {
		boolean isDataSavedInRedis = false;
		try {
			ValueOperations<String, Object> ops = this.redisTemplate.opsForValue();
			ops.set(key, value, Duration.ofMillis(timeoutInMiliSeconds));
			isDataSavedInRedis = true;
		} catch (Exception e) {
			log.info("unable to save given data in redis database:- {} ", e.getMessage());
		}
		return isDataSavedInRedis;
	}
}
