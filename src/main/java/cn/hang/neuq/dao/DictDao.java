package cn.hang.neuq.dao;

import cn.hang.neuq.base.BaseDao;
import cn.hang.neuq.entity.po.Dict;
import cn.hang.neuq.entity.po.DictExample;
import cn.hang.neuq.exception.InfoException;
import cn.hang.neuq.mapper.DictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lihang15
 * @description
 * @create 2019-01-26 01:04
 **/
@Repository
public class DictDao extends BaseDao<Dict, Long, DictExample> {
    private final DictMapper dictMapper;

    @Autowired
    public DictDao(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
        super.mapper = dictMapper;
    }

    public List<Dict> listByType(String type) {
        DictExample dictExample = new DictExample();
        DictExample.Criteria criteria = dictExample.createCriteria();
        criteria.andTypeEqualTo(type);
        return dictMapper.selectByExample(dictExample);
    }

    public Dict getByType(String type) {
        DictExample dictExample = new DictExample();
        DictExample.Criteria criteria = dictExample.createCriteria();
        criteria.andTypeEqualTo(type);
        List<Dict> dictList = dictMapper.selectByExample(dictExample);
        if (dictList.size() == 1) {
            return dictList.get(0);
        }
        throw new InfoException("getByType should be one but find more then one");
    }
}
