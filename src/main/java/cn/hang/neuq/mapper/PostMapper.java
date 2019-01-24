package cn.hang.neuq.mapper;

import cn.hang.neuq.base.MyBatisBaseDao;
import cn.hang.neuq.entity.po.Post;
import cn.hang.neuq.entity.po.PostExample;
import org.springframework.stereotype.Repository;

/**
 * PostMapper继承基类
 */
@Repository
public interface PostMapper extends MyBatisBaseDao<Post, Long, PostExample> {
}