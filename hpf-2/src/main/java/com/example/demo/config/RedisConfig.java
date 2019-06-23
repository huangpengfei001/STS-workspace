//package com.example.demo.config;
//
//import java.io.Serializable;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//
//@Configuration
//public class RedisConfig {
//
//	@Value("${spring.redis.host}")
//	private String redisHost;
//	@Value("${spring.redis.port}")
//	private int redisPort;
//	@Value("${spring.redis.timeout}")
//	private int redisTimeout;
//	@Value("${spring.redis.password}")
//	private String redisPassword;
//	@Value("${spring.redis.database}")
//	private int redisDatabase;
//
//	@Value("${spring.redis.jedis.pool.min-idle}")
//	private int jedisMinIdle;
//	@Value("${spring.redis.jedis.pool.max-idle}")
//	private int jedisMaxIdle;
//	@Value("${spring.redis.jedis.pool.max-active}")
//	private int jedisMaxActive;
//	@Value("${spring.redis.jedis.pool.max-wait}")
//	private int jedisMaxWait;
//
//	@Value("${spring.redis.cluster.max-redirects}")
//	private int clusterMaxRedirects;
//	@Value("${spring.redis.cluster.nodes}")
//	private List<String> clusterNodes;
//
//	@Bean
//	public RedisConnectionFactory connectionFactory() {
//		JedisPoolConfig poolConfig = new JedisPoolConfig();
//		poolConfig.setMaxTotal(jedisMaxActive);
//		poolConfig.setMaxIdle(jedisMaxIdle);
//		poolConfig.setMaxWaitMillis(jedisMaxWait);
//		poolConfig.setMinIdle(jedisMinIdle);
//		poolConfig.setTestOnBorrow(true);
//		poolConfig.setTestOnReturn(false);
//		poolConfig.setTestWhileIdle(true);
////		JedisClientConfiguration clientConfig = JedisClientConfiguration.builder().usePooling().poolConfig(poolConfig)
////				.and().readTimeout(Duration.ofMillis(redisTimeout)).build();
////		// 单点redis
////		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
////		log.debug(JSON.toJSONString(redisStandaloneConfiguration));
////		redisStandaloneConfiguration.setHostName(redisHost);
////		redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPassword));
////		redisStandaloneConfiguration.setPort(redisPort);
////		redisStandaloneConfiguration.setDatabase(redisDatabase);
////		// 哨兵redis
////		RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
////		log.debug(JSON.toJSONString(redisSentinelConfiguration));
////		// 集群redis
////		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
////		log.debug(JSON.toJSONString(redisClusterConfiguration));
////		redisClusterConfiguration.setPassword(RedisPassword.of(redisPassword));
////		redisClusterConfiguration.setMaxRedirects(clusterMaxRedirects);
////		redisClusterConfiguration.setClusterNodes(null);// TODO
//		return new JedisConnectionFactory(redisStandaloneConfiguration, clientConfig);
//	}
//
//	@Bean
//	public RedisTemplate getRedisTemplate() {
//		RedisTemplate redisTemplate = new RedisTemplate<>();
//		return redisTemplate;
//	}
//
//	@Bean
//	public ValueOperations<Serializable, Object> getRedisTemplate(RedisTemplate redisTemplate) {
//		return redisTemplate.opsForValue();
//	}
//
//}
