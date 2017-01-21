package cn.edu.ncut.test;

import cn.edu.ncut.utils.JedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author NikoBelic
 * @create 21/01/2017 12:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class TestRedis
{
    @Autowired
    private JedisUtils jedisUtils;

    @Test
    public void testString()
    {
        System.out.println(jedisUtils.set("address", "ShangHai"));
        System.out.println(jedisUtils.get("address"));
        try
        {
            System.out.println(jedisUtils.incrBy("address", 100));
        } catch (Exception e)
        {
            System.out.println(e.getMessage() + "自增操作异常");
        }

        jedisUtils.set("myint", "10");
        System.out.println(jedisUtils.incrBy("myint", 100));
    }

    @Test
    public void testList()
    {
        System.out.println(jedisUtils.lPushObj("strList", "NikoBelic","Tom","Helen"));
        //System.out.println((String) jedisUtils.lPop("strList"));
        //System.out.println((String) jedisUtils.lPop("strList"));
        List<String> strList = jedisUtils.lRange("strList", 0, -1);
        for (String s : strList)
        {
            System.out.println(s);
        }

        //System.out.println(jedisUtils.lPushObj("objList", new User("NikoBelic", 18), new User("Tom", 15), new User("Marry", 20)));
        //System.out.println((User) jedisUtils.lPop("objList"));
        //System.out.println((User) jedisUtils.lPop("objList"));
        //List<User> objList = jedisUtils.lRange("objList", 0, -1);
        //for (User user : objList)
        //{
        //    System.out.println(user);
        //}
    }

    @Test
    public void testSet()
    {
        //System.out.println(jedisUtils.sAdd("strSet", "NikoBelic","Tom","Helen"));
        //Set<String> strList = jedisUtils.sMembers("strSet");
        //for (String s : strList)
        //{
        //    System.out.println(s);
        //}

        //System.out.println(jedisUtils.sAdd("objSet", new User("NikoBelic", 18), new User("Tom", 15), new User("Marry", 20)));
        //Set<User> objList = jedisUtils.sMembers("objSet");
        //for (User user : objList)
        //{
        //    System.out.println(user);
        //}
        //User myObj = new User("Exist Test",20);
        //jedisUtils.sAdd("objSet",myObj);
        System.out.println(jedisUtils.sIsMember("objSet",new User("ExistTest",20)));

    }

    @Test
    public void testHash()
    {
        //Map<String,String> map = new HashMap<>();
        //map.put("A","1");
        //map.put("B","2");
        //map.put("C","3");
        //System.out.println(jedisUtils.hmSet("strHash",map));

        //List<String> strs = jedisUtils.hmGet("strHash", "A", "B", "C");
        //for (String str : strs)
        //{
        //    System.out.println(str);
        //}



        Map<String,User> map = new HashMap<>();
        map.put("user1",new User("Niko",18));
        map.put("user2",new User("Tom",20));
        map.put("user3",new User("Marry",15));
        System.out.println(jedisUtils.hmSet("objHash",map));
        List<User> userList = jedisUtils.hmGet("objHash", "user1", "user2", "user3");
        for (User user : userList)
        {
            System.out.println(user);
        }

    }

}

class User implements Serializable
{
    private String name;
    private int age;

    public User(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}