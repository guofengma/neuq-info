package cn.hang.neuq.mapper;

import cn.hang.neuq.base.MyBatisBaseDao;
import cn.hang.neuq.entity.po.UserLikePost;
import cn.hang.neuq.entity.po.UserLikePostExample;
import cn.hang.neuq.entity.po.UserLikePostKey;
import org.springframework.stereotype.Repository;

/**
 * UserLikePostMapper继承基类
 */
@Repository
public interface UserLikePostMapper extends MyBatisBaseDao<UserLikePost, UserLikePostKey, UserLikePostExample> {
}