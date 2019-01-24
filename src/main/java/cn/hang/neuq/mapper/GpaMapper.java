package cn.hang.neuq.mapper;

import cn.hang.neuq.base.MyBatisBaseDao;
import cn.hang.neuq.entity.po.Gpa;
import cn.hang.neuq.entity.po.GpaExample;
import org.springframework.stereotype.Repository;

/**
 * GpaMapper继承基类
 */
@Repository
public interface GpaMapper extends MyBatisBaseDao<Gpa, Long, GpaExample> {
}