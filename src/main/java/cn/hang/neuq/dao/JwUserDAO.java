package cn.hang.neuq.dao;

import cn.hang.neuq.base.BaseDao;
import cn.hang.neuq.entity.po.User;
import cn.hang.neuq.entity.po.UserExample;
import cn.hang.neuq.entity.po.UserJwInfo;
import cn.hang.neuq.entity.po.UserJwInfoExample;
import cn.hang.neuq.mapper.UserJwInfoMapper;
import cn.hang.neuq.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author lihang15
 */
@Repository
public class JwUserDAO extends BaseDao<UserJwInfo, Long, UserJwInfoExample> {


    private final UserJwInfoMapper userJwInfoMapper;

    @Autowired
    public JwUserDAO(UserJwInfoMapper userJwInfoMapper) {
        this.userJwInfoMapper = userJwInfoMapper;
        super.mapper = userJwInfoMapper;
    }


    public UserJwInfo getInfoByUserId(long userId) {
        UserJwInfoExample userJwInfoExample = new UserJwInfoExample();
        UserJwInfoExample.Criteria criteria = userJwInfoExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<UserJwInfo> userJwInfoList = userJwInfoMapper.selectByExample(userJwInfoExample);
        if (userJwInfoList.size() == 1) {
            return userJwInfoList.get(0);
        }
        return null;
    }

    public int updateByUserId(UserJwInfo userJwInfo) {
        UserJwInfoExample userJwInfoExample = new UserJwInfoExample();
        UserJwInfoExample.Criteria criteria = userJwInfoExample.createCriteria();
        criteria.andUserIdEqualTo(userJwInfo.getUserId());
        return userJwInfoMapper.updateByExampleSelective(userJwInfo, userJwInfoExample);
    }


}