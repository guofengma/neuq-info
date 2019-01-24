package cn.hang.neuq.service;

import cn.hang.neuq.base.BaseService;
import cn.hang.neuq.common.Response;
import cn.hang.neuq.common.ResponseMessageEnum;
import cn.hang.neuq.constant.CommonConstant;
import cn.hang.neuq.constant.InfoProperties;
import cn.hang.neuq.dao.JwUserDAO;
import cn.hang.neuq.entity.dto.JwApiResDTO;
import cn.hang.neuq.entity.po.UserJwInfo;
import cn.hang.neuq.entity.vo.JwAuthVO;
import cn.hang.neuq.entity.vo.UserJwInfoVO;
import cn.hang.neuq.exception.InfoException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @author lihang15
 * @description
 * @create 2019-01-22 22:19
 **/
@Service
@Slf4j
public class AuthService extends BaseService {

    private final InfoProperties infoProperties;

    @Autowired
    private JwUserDAO jwUserDAO;

    @Autowired
    public AuthService(InfoProperties infoProperties) {
        this.infoProperties = infoProperties;
    }

    public Response login(JwAuthVO jwAuthVO) {
        long userId = sessionUtils.getUserId();
        String result = null;
        try {
            RestTemplate client = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", jwAuthVO.getUsername());
            jsonObject.put("password", jwAuthVO.getPassword());
            HttpEntity<String> formEntity = new HttpEntity<>(jsonObject.toString(), headers);
            result = client.postForObject(infoProperties.getAuthHost(), formEntity, String.class);
        } catch (RestClientException e) {
            log.error("network error", e);
            return Response.error(ResponseMessageEnum.NETWORK_ERROR);
        }
        log.info("jwxt auth res userId={},res={}", userId, result);
        JwApiResDTO<AuthRes> jwApiResDTO = JSON.parseObject(result, new TypeReference<JwApiResDTO<AuthRes>>() {
        });
        if (jwApiResDTO != null && jwApiResDTO.getCode().equals(CommonConstant.JW_NETWORK_ERROR_CODE)) {
            throw new InfoException("教务系统繁忙");
        }
        if (jwApiResDTO != null && jwApiResDTO.getCode() == 0) {
            UserJwInfo userJwInfo = new UserJwInfo();
            userJwInfo.setUserId(userId);
            userJwInfo.setIsJwAuth(CommonConstant.USER_JW_AUTH_PASS);
            userJwInfo.setJwPassword(jwApiResDTO.getData().getPassword());
            userJwInfo.setGrade(jwApiResDTO.getData().getGrade());
            userJwInfo.setUserType(jwApiResDTO.getData().getUserType());
            userJwInfo.setStudentId(jwApiResDTO.getData().getStudentId());
            userJwInfo.setStudentName(jwApiResDTO.getData().getStudentName());
            userJwInfo.setCollege(jwApiResDTO.getData().getCollege());
            userJwInfo.setProfession(jwApiResDTO.getData().getProfession());
            userJwInfo.setJwUsername(jwAuthVO.getUsername());
            userJwInfo.setGmtCreate(new Date());
            jwUserDAO.updateByUserId(userJwInfo);
            UserJwInfoVO userJwInfoVO = new UserJwInfoVO();
            BeanUtils.copyProperties(userJwInfo, userJwInfoVO);
            return Response.success(userJwInfoVO);
        } else {
            UserJwInfo userJwInfo = new UserJwInfo();
            userJwInfo.setUserId(userId);
            userJwInfo.setIsJwAuth(CommonConstant.USER_JW_AUTH_NOT_PASS);
            userJwInfo.setJwPassword("");
            jwUserDAO.updateByUserId(userJwInfo);
            return Response.error("教务系统认证失败");
        }
    }

    @Data
    private static class AuthRes {
        private String password;
        private String grade;
        private String studentId;
        private String studentName;
        private String userType;
        private String profession;
        private String college;
    }

}



