package cn.edu.ncut.dmes;

import cn.edu.ncut.mapper.UserMapper;
import cn.edu.ncut.model.User;
import cn.edu.ncut.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class SSMTest {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Resource
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@Test
	public void test()
	{
		List<User> userList = userService.findAll();
		for (User user : userList) {
			System.out.println(user);
		}
	}
}
