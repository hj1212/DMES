package cn.edu.ncut.service;

import cn.edu.ncut.model.User;

import java.util.List;

/**
 * Created by lixiwei-mac on 16/4/2.
 */
public interface UserService {
    public List<User> findAll();
    public int addUser(User user);
}
