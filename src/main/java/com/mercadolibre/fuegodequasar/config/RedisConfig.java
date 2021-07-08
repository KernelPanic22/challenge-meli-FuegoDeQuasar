package com.mercadolibre.fuegodequasar.config;

import com.mercadolibre.fuegodequasar.dto.SatelliteSplitDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

	@Value("${prisma.api.oauth.redis.server}")
	private String redisServer;

	@Value("${prisma.api.oauth.redis.port}")
	private Integer redisPort;

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory(new RedisStandaloneConfiguration(redisServer, redisPort));
	}

	@Bean
	public RedisTemplate<String, SatelliteSplitDTO> redisTemplate() {
		RedisTemplate<String, SatelliteSplitDTO> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(SatelliteSplitDTO.class));
		redisTemplate.setDefaultSerializer(new StringRedisSerializer());

		return redisTemplate;
	}

}
