package cn.hang.neuq.mapper;

import cn.hang.neuq.base.MyBatisBaseDao;
import cn.hang.neuq.entity.po.Comment;
import cn.hang.neuq.entity.po.CommentExample;
import org.springframework.stereotype.Repository;

/**
 * CommentMapper继承基类
 */
@Repository
public interface CommentMapper extends MyBatisBaseDao<Comment, Long, CommentExample> {
}