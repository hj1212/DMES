package cn.edu.ncut.test;

import cn.edu.ncut.dao.UserDao;
import cn.edu.ncut.model.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lixiwei-mac
 * @create 12:01
 */
@Component
public class TestMongoUser {

    @Autowired
    private static UserDao userDao;
    private static ClassPathXmlApplicationContext app;
    private static String collectionName;


    @BeforeClass
    public static void initSpring() {
        try {
            app = new ClassPathXmlApplicationContext(new String[]{"classpath:spring/applicationContext*.xml"});
            userDao = (UserDao) app.getBean("userDaoImpl");
            collectionName = "users";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {

        //添加一百个user
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setId(i);
            user.setAge(i);
            user.setName("lxw" + i);
            userDao.insert(user, collectionName);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("maxAge", 50);
        List<User> list = userDao.findAll(params, collectionName);
        System.out.println("user.count()==" + list.size());
    }

    @Test
    public void testUpdate() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", 1);//此处Value必须要和Model数据类型一致
        User user = userDao.findOne(params, collectionName);
        System.out.println("user.name===" + user.getName());
        System.out.println("=============update==================");
        params.put("name", "hello");
        userDao.update(params, collectionName);
        user = userDao.findOne(params, collectionName);
        System.out.println("user.name===" + user.getName());
    }

    @Test
    public void testRemove()
    {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", 2);
        userDao.remove(params, collectionName);
        User user = userDao.findOne(params, collectionName);
        System.out.println("user==" + user);
    }
}