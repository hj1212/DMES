package cn.edu.ncut.test;

import cn.edu.ncut.model.User;
import cn.edu.ncut.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author NikoBelic
 * @create 21:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class SSMTest
{
    @Resource
    private UserService userService;
    @Test
    public void test()
    {
        List<User> userList = userService.findAll();
        for (User user : userList)
        {
            System.out.println(user.toString());
        }
    }
}
