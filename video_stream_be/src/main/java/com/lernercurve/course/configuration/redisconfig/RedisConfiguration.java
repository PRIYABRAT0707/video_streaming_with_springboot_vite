package com.lernercurve.course.configuration.redisconfig;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfiguration {
	@Bean
	public RedisConnectionFactory lettuceConnectionFactory() {
		RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration().master("courseMaster")
				.sentinel("localhost", 6379);
//	  .sentinel("127.0.0.1", 26380);
		return new LettuceConnectionFactory("localhost", 6379);
	}

	@Bean
	RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {

		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		return template;
	}

	@Bean
	CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
				.serializeValuesWith(
						RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));

		return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(cacheConfig).build();
	}

}
