package cn.edu.ncut.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.*;

/**
 * Jedis工具类
 *
 * @author NikoBelic
 * @create 21/01/2017 13:41
 */
@Component
public class JedisUtils
{

    @Autowired
    Jedis jedis;

    private JedisUtils()
    {
    }

    // ********************** String Oprations *************************
    public String set(String key, String val)
    {
        return jedis.set(key, val);
    }

    public String get(String key)
    {
        return jedis.get(key);
    }

    public Long incrBy(String key, Integer increment)
    {
        return jedis.incrBy(key, increment);
    }

    public Long decrBy(String key, Integer decrement)
    {
        return jedis.incrBy(key, decrement);
    }


    // ********************** List Oprations *************************
    /**
     * 从左侧推入元素
     * @Author NikoBelic
     * @Date 21/01/2017 17:47
     */
    public <T extends Serializable> Long lPushObj(String key, T... obj)
    {
        byte[] serializedObj;
        long count = 0;
        for (T t : obj)
        {
            serializedObj = SerializationUtil.serialize(t);
            jedis.lpush(key.getBytes(),serializedObj);
            count++;
        }
        return count;
    }
    /**
     * 从列表左侧弹出数据
     * @Author NikoBelic
     * @Date 21/01/2017 17:47
     */
    public <T extends Serializable> T lPop(String key)
    {
        byte[] obj = jedis.lpop(key.getBytes());
        return SerializationUtil.deserialize(obj);
    }
    /**
     * 获取指定范围内的列表元素
     * @Author NikoBelic
     * @Date 21/01/2017 17:47
     */
    public <T extends Serializable> List<T> lRange(String key,int from,int to)
    {
        List<byte[]> byteList = jedis.lrange(key.getBytes(), from, to);
        List<T> objList = null;
        if (byteList.size() > 0)
        {
            objList = new ArrayList<T>();
            for (byte[] obj : byteList)
            {
                objList.add(SerializationUtil.deserialize(obj));
            }
        }
        return objList;
    }




    // ********************** Set Oprations *************************
    /**
     * 向集合中添加元素
     * @Author NikoBelic
     * @Date 21/01/2017 18:06
     */
    public <T extends Serializable> Long sAdd(String key, T... obj)
    {
        byte[] serializedObj;
        long count = 0;
        for (T t : obj)
        {
            serializedObj = SerializationUtil.serialize(t);
            jedis.sadd(key.getBytes(),serializedObj);
            count++;
        }
        return count;
    }
    /**
     * 返回集合中的所有元素
     * @Author NikoBelic
     * @Date 21/01/2017 18:06
     */
    public <T extends Serializable> Set<T> sMembers(String key)
    {
        Set<byte[]> byteList = jedis.smembers(key.getBytes());
        Set<T> objList = null;
        if (byteList.size() > 0)
        {
            objList = new HashSet<T>();
            for (byte[] obj : byteList)
            {
                objList.add(SerializationUtil.deserialize(obj));
            }
        }
        return objList;
    }
    /**
     * 判断对象是否存在于集合中
     * 注意:判断标准是列化后的字符串是否相同,即时不同的对象但序列化结果相同也将返回true
     * @Author NikoBelic
     * @Date 21/01/2017 18:04
     */
    public <T extends Serializable> Boolean sIsMember(String key, T obj)
    {
        byte[] serializedObj = SerializationUtil.serialize(obj);
        return jedis.sismember(key.getBytes(),serializedObj);
    }
    // ********************** Hash Oprations *************************
    /**
     * 向哈希表存储键值对数据
     * @Author NikoBelic
     * @Date 21/01/2017 18:41
     */
    public <T extends Serializable> String hmSet(String key, Map<String,T> map)
    {
        Map<byte[],byte[]> objMap;
        if (map.size() > 0)
        {
            objMap = new HashMap<>();
            for (Map.Entry<String, T> entry : map.entrySet())
            {
                objMap.put(entry.getKey().getBytes(), SerializationUtil.serialize(entry.getValue()));
            }
            return jedis.hmset(key.getBytes(), objMap);
        }
        return null;
    }
    /**
     * 从Hash中取出键值对数据
     * @Author NikoBelic
     * @Date 21/01/2017 18:41
     */
    public <T extends Serializable> List<T> hmGet(String key, String... fields)
    {
        List<T> resObjs = new ArrayList<T>();
        for (String field : fields)
        {
            resObjs.add(SerializationUtil.deserialize(jedis.hget(key.getBytes(),field.getBytes())));
        }
        return resObjs;
    }
    // ********************** ZSet Oprations *************************

}
