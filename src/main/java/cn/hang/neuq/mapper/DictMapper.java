package cn.hang.neuq.mapper;

import cn.hang.neuq.base.MyBatisBaseDao;
import cn.hang.neuq.entity.po.Dict;
import cn.hang.neuq.entity.po.DictExample;
import org.springframework.stereotype.Repository;

/**
 * DictMapper继承基类
 */
@Repository
public interface DictMapper extends MyBatisBaseDao<Dict, Long, DictExample> {
}