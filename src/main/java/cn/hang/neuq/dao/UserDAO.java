package cn.hang.neuq.dao;

import cn.hang.neuq.base.BaseDao;
import cn.hang.neuq.entity.po.User;
import cn.hang.neuq.entity.po.UserExample;
import cn.hang.neuq.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author lihang15
 */
@Repository
public class UserDAO extends BaseDao<User, Long, UserExample> {


    private final UserMapper userMapper;

    @Autowired
    public UserDAO(UserMapper userMapper) {
        this.userMapper = userMapper;
        super.mapper = userMapper;
    }

    public User getUserByOpenId(String openId) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }


}