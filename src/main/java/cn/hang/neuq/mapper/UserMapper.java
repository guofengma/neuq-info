package cn.hang.neuq.mapper;

import cn.hang.neuq.base.MyBatisBaseDao;
import cn.hang.neuq.entity.po.User;
import cn.hang.neuq.entity.po.UserExample;
import org.springframework.stereotype.Repository;

/**
 * UserMapper继承基类
 */
@Repository
public interface UserMapper extends MyBatisBaseDao<User, Long, UserExample> {
}