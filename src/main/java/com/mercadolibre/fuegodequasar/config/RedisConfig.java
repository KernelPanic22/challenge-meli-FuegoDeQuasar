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

	@Value("${redis.server}")
	private String redisServer;

	@Value("${redis.port}")
	private Integer redisPort;

	@Value("${redis.password}")
	private String redisPassword;

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisServer, redisPort);
		configuration.setPassword(redisPassword);
		return new JedisConnectionFactory(configuration);
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
