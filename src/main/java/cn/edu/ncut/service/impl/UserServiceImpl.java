package cn.edu.ncut.service.impl;

import cn.edu.ncut.mapper.UserMapper;
import cn.edu.ncut.model.User;
import cn.edu.ncut.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lixiwei-mac
 * @create 22:50
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }
}
