package cn.edu.ncut.dao;

import cn.edu.ncut.model.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lixiwei-mac
 * @create 11:55
 */
@Repository("userDaoImpl")
public class UserDaoImpl extends MongoDaoSupport<User> implements UserDao {

}