package cn.hang.neuq.mapper;

import cn.hang.neuq.base.MyBatisBaseDao;
import cn.hang.neuq.entity.po.UserJwInfo;
import cn.hang.neuq.entity.po.UserJwInfoExample;
import org.springframework.stereotype.Repository;

/**
 * UserJwInfoMapper继承基类
 */
@Repository
public interface UserJwInfoMapper extends MyBatisBaseDao<UserJwInfo, Long, UserJwInfoExample> {
}