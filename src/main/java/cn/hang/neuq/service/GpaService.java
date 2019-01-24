package cn.hang.neuq.service;

import cn.hang.neuq.base.BaseService;
import cn.hang.neuq.common.Response;
import cn.hang.neuq.common.ResponseMessageEnum;
import cn.hang.neuq.constant.CommonConstant;
import cn.hang.neuq.constant.InfoProperties;
import cn.hang.neuq.dao.GpaDAO;
import cn.hang.neuq.dao.JwUserDAO;
import cn.hang.neuq.dao.UserDAO;
import cn.hang.neuq.entity.po.Gpa;
import cn.hang.neuq.entity.po.User;
import cn.hang.neuq.entity.po.UserJwInfo;
import cn.hang.neuq.util.SessionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

/**
 * @author lihang15
 * @description
 * @create 2019-01-22 19:13
 **/
@Service
@Slf4j
public class GpaService extends BaseService {

    private final SessionUtils sessionUtils;

    private final UserDAO userDAO;

    private final GpaDAO gpaDao;

    private final JwUserDAO jwUserDAO;

    private final InfoProperties infoProperties;

    @Autowired
    public GpaService(SessionUtils sessionUtils, UserDAO userDAO, InfoProperties infoProperties, GpaDAO gpaDao, JwUserDAO jwUserDAO) {
        this.sessionUtils = sessionUtils;
        this.userDAO = userDAO;
        this.infoProperties = infoProperties;
        this.gpaDao = gpaDao;
        this.jwUserDAO = jwUserDAO;
    }

    public Response refresh() {
        Long userId = sessionUtils.getUserId();
        User user = userDAO.selectByPrimaryKey(userId);
        UserJwInfo userJwInfo = jwUserDAO.getInfoByUserId(userId);
        String res = null;
        try {
            RestTemplate client = new RestTemplate();
            res = client.getForObject(infoProperties.getGpaHost(), String.class, userJwInfo.getJwUsername(), userJwInfo.getJwPassword());
        } catch (RestClientException e) {
            log.error("network error", e);
            return Response.error(ResponseMessageEnum.NETWORK_ERROR);
        }
        JSONObject jsonObject = JSON.parseObject(res);
        JSONArray gpaArray = jsonObject.getJSONArray("data");
        List<Gpa> gpaList = gpaArray.toJavaList(Gpa.class);
        float gpaTotal = 0;
        float creditTotal = 0;
        for (Gpa gpa : gpaList) {
            creditTotal += gpa.getCredit();
            if (gpa.getGpa() != null && gpa.getCredit() != null) {
                gpaTotal += gpa.getGpa() * gpa.getCredit();
            }
            gpa.setUserId(user.getId());
            Gpa oldGpa = gpaDao.getGpaByUserIdAndCourseIdAndExamType(userId, gpa.getCourseId(), gpa.getExamType());
            if (oldGpa == null) {
                gpa.setClassId(userJwInfo.getClassId());
                gpaDao.insertSelective(gpa);
            } else {
                gpa.setClassId(userJwInfo.getClassId());
                oldGpa.setScore(gpa.getScore());
                oldGpa.setGpa(gpa.getGpa());
                oldGpa.setCredit(gpa.getCredit());
                oldGpa.setIsExamInvalid(gpa.getIsExamInvalid());
                oldGpa.setSemester(gpa.getSemester());
                oldGpa.setGmtModified(new Date());
                gpaDao.updateByPrimaryKeySelective(oldGpa);
            }
        }
        if (creditTotal != 0) {
            float weightAverageGpa = (float) Math.round((gpaTotal / creditTotal * 10000)) / 10000;
            jwUserDAO.updateByPrimaryKeySelective(new UserJwInfo(userJwInfo.getId(), weightAverageGpa));
        }

        return Response.success(gpaList);
    }

    public Response<List<Gpa>> list(String semester) {
        List<Gpa> gpaList = gpaDao.listBySemester(semester, sessionUtils.getUserId());
        return Response.success(gpaList);
    }
}
