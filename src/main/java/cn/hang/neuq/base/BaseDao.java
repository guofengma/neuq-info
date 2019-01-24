package cn.hang.neuq.base;


import java.io.Serializable;

/**
 * @author lihang15
 */
public class BaseDao<Model extends Serializable, PK, E> {

    protected MyBatisBaseDao<Model, PK, E> mapper;

    public int insert(Model record) {
        return mapper.insert(record);
    }

    public int insertSelective(Model record) {
        return mapper.insertSelective(record);

    }

    public Model selectByPrimaryKey(PK id) {
        return mapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKey(Model record) {
        return mapper.updateByPrimaryKey(record);
    }

    public int updateByPrimaryKeySelective(Model record) {
        return mapper.updateByPrimaryKeySelective(record);
    }
}