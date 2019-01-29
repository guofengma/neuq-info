package cn.hang.neuq.dao;

import cn.hang.neuq.base.BaseDao;
import cn.hang.neuq.constant.CommonConstant;
import cn.hang.neuq.entity.po.Gpa;
import cn.hang.neuq.entity.po.GpaExample;
import cn.hang.neuq.entity.po.User;
import cn.hang.neuq.entity.po.UserExample;
import cn.hang.neuq.mapper.GpaMapper;
import cn.hang.neuq.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author lihang15
 */
@Repository
public class GpaDAO extends BaseDao<Gpa, Long, GpaExample> {


    private final GpaMapper gpaMapper;

    @Autowired
    public GpaDAO(GpaMapper gpaMapper) {
        this.gpaMapper = gpaMapper;
        super.mapper = gpaMapper;
    }

    public Gpa getGpaByUserIdAndCourseIdAndExamType(Long userId, String courseId, String examType) {
        GpaExample gpaExample = new GpaExample();
        GpaExample.Criteria criteria = gpaExample.createCriteria();
        criteria.andUserIdEqualTo(userId).andCourseIdEqualTo(courseId).andExamTypeEqualTo(examType).andStatusEqualTo(CommonConstant.DATA_STATUS_NORMAL);
        List<Gpa> gpaList = gpaMapper.selectByExample(gpaExample);
        if (gpaList.size() == 1) {
            return gpaList.get(0);
        }
        return null;
    }

    public List<Gpa> listBySemester(String semester, Long userId) {
        GpaExample gpaExample = new GpaExample();
        GpaExample.Criteria criteria = gpaExample.createCriteria();
        criteria.andSemesterEqualTo(semester).andUserIdEqualTo(userId).andStatusEqualTo(CommonConstant.DATA_STATUS_NORMAL);
        return gpaMapper.selectByExample(gpaExample);
    }

    public int updateByUserId(Gpa gpa) {
        GpaExample gpaExample = new GpaExample();
        GpaExample.Criteria criteria = gpaExample.createCriteria();
        criteria.andUserIdEqualTo(gpa.getUserId());
        return gpaMapper.updateByExampleSelective(gpa, gpaExample);
    }
}