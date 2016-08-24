package cn.edu.ncut.test;

import cn.edu.ncut.dao.ArticleDao;
import cn.edu.ncut.dao.UserDao;
import cn.edu.ncut.model.Article;
import cn.edu.ncut.model.User;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author lixiwei-mac
 * @create 12:01
 */
@Component
public class TestMongoUser {

    @Autowired
    private static UserDao userDao;
    @Autowired
    private static ArticleDao articleDao;
    private static ClassPathXmlApplicationContext app;
    private static String collectionName;


    @BeforeClass
    public static void initSpring() {
        try {
            app = new ClassPathXmlApplicationContext(new String[]{"classpath:spring/applicationContext*.xml"});
            userDao = (UserDao) app.getBean("userDaoImpl");
            articleDao = (ArticleDao) app.getBean("articleDaoImpl");
            collectionName = "users";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {

        //添加一百个user
        for (int i = 0; i < 1; i++) {
            User user = new User();
            user.setId(i);
            user.setAge(i);
            Random random = new Random();
            user.setName("2016年08月24日测试" + random.nextInt(10000));
            userDao.insert(user, collectionName);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id",1);
        User user = userDao.findOne(params, collectionName);
        System.out.println(user.toString());
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
    @Test
    public void findAll()
    {
        List<User> mongoUsers = userDao.findAll(collectionName);
        for (User user : mongoUsers)
        {
            System.out.println(user.toString());
        }
    }
    @Test
    public void testRemoveCollection()
    {
        userDao.removeCollection(collectionName);
    }
    @Test
    public void addArticles() throws IOException
    {
        String collection = "articles";
        String path = "/Users/lixiwei-mac/Desktop/";
        for (int i = 1 ; i <= 6 ; i++)
        {
            File file = new File(path + i + ".txt");
            String content = FileUtils.readFileToString(file);
            Article article = new Article();
            article.setId(i);
            article.setContent(content);
            articleDao.insert(article,collection);
        }
    }
}

