package cn.edu.ncut.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author NikoBelic
 * @create 21/01/2017 12:11
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport
{
    @Value("${redis_host}")
    String redisHost;

    @Value("${redis_port}")
    Integer redisPort;

    @Value("${redis_keynum}")
    Integer redisKeynum;

    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


    public RedisCacheConfig()
    {
        System.out.println("Java配置类初始化");
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig()
    {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis(60 * 1000);
        jedisPoolConfig.setMaxTotal(1000);
        jedisPoolConfig.setMaxIdle(100);
        return jedisPoolConfig;
    }
    @Bean
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig)
    {
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,redisHost,redisPort,999999999);
        return jedisPool;
    }
    @Bean
    public Jedis jedis(JedisPool jedisPool)
    {
        Jedis jedis = jedisPool.getResource();
        jedis.select(redisKeynum);
        return jedis;
    }


    @Bean
    public JedisConnectionFactory redisConnectionFactory()
    {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        System.out.println(redisHost + ":" + redisPort + "-" + redisKeynum);
        logger.info(redisHost + ":" + redisPort + "-" + redisKeynum);
        factory.setHostName(redisHost);
        factory.setPort(redisPort);
        factory.setDatabase(redisKeynum);
        return factory;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<String,String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate)
    {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(0);;
        return cacheManager;
    }

}