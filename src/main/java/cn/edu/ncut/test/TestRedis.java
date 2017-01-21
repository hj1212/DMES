package cn.edu.ncut.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import javax.ws.rs.core.Context;

/**
 * @author NikoBelic
 * @create 21/01/2017 12:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class TestRedis
{
    @Autowired
    Jedis jedis;

    @Test
    public void test()
    {
        System.out.println(jedis);
        System.out.println(jedis.get("mykey"));
    }

}
