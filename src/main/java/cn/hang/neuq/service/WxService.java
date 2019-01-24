package cn.hang.neuq.service;

import cn.hang.neuq.constant.CommonConstant;
import cn.hang.neuq.dao.JwUserDAO;
import cn.hang.neuq.entity.po.UserJwInfo;
import cn.hang.neuq.util.SessionUtils;
import cn.hang.neuq.common.Response;
import cn.hang.neuq.common.ResponseMessageEnum;
import cn.hang.neuq.constant.CacheConstant;
import cn.hang.neuq.constant.WxProperties;
import cn.hang.neuq.dao.UserDAO;
import cn.hang.neuq.entity.dto.Code2SessionDTO;
import cn.hang.neuq.entity.po.User;
import cn.hang.neuq.entity.vo.DecodeUserInfoVO;
import cn.hang.neuq.util.AES;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static cn.hang.neuq.constant.CommonConstant.USER_JW_AUTH_NOT_PASS;
import static cn.hang.neuq.constant.CommonConstant.USER_STATUS_NORMAL;

/**
 * @author lihang15
 */
@Service
@Slf4j
public class WxService {

    private final WxProperties wxProperties;

    private final StringRedisTemplate redisTemplate;

    private final UserDAO userDAO;

    private final JwUserDAO jwUserDAO;

    private final SessionUtils sessionUtils;

    @Autowired
    public WxService(WxProperties wxProperties, StringRedisTemplate redisTemplate, UserDAO userDAO, SessionUtils sessionUtils, JwUserDAO jwUserDAO) {
        Assert.notNull(wxProperties, "wxProperties must not be null!");
        Assert.notNull(redisTemplate, "redisTemplate must not be null!");
        this.wxProperties = wxProperties;
        this.redisTemplate = redisTemplate;
        this.userDAO = userDAO;
        this.sessionUtils = sessionUtils;
        this.jwUserDAO = jwUserDAO;
    }


    public Response getWxSession(String wxCode) {
        Code2SessionDTO code2SessionDTO = getSessionKey(wxCode);
        log.info("get session by code res:{}", code2SessionDTO);
        if (code2SessionDTO.getErrcode() != null && code2SessionDTO.getErrcode() != 0) {
            log.error("code2Session error code={}", wxCode);
            return Response.error("code2Session 失败");
        }
        String accessToken = UUID.randomUUID().toString().replace("-", "");
        User user = userDAO.getUserByOpenId(code2SessionDTO.getOpenid());
        if (user == null) {
            user = new User();
            user.setOpenId(code2SessionDTO.getOpenid());
            user.setStatus(USER_STATUS_NORMAL);
            userDAO.insertSelective(user);
            UserJwInfo jwInfo = new UserJwInfo();
            jwInfo.setUserId(user.getId());
            jwInfo.setIsJwAuth(CommonConstant.USER_JW_AUTH_NOT_PASS);
            jwUserDAO.insertSelective(jwInfo);
        }
        redisTemplate.opsForValue().set(String.format(CacheConstant.ACCESS_TOKEN, accessToken), code2SessionDTO.getOpenid() + "#" + code2SessionDTO.getSessionKey() + "#" + user.getId());
        redisTemplate.expire(String.format(CacheConstant.ACCESS_TOKEN, accessToken), 48, TimeUnit.HOURS);
        redisTemplate.opsForValue().set(String.format(CacheConstant.USER_ACCESS_TOKEN, user.getId()), accessToken);
        return Response.success(accessToken);
    }

    public Response decodeUserInfo(DecodeUserInfoVO decodeUserInfoVO) {
        String openId = sessionUtils.getOpenId();
        String sessionKey = sessionUtils.getSessionKey();
        try {
            byte[] resultByte = AES.decrypt(Base64.decodeBase64(decodeUserInfoVO.getEncryptedData()), Base64.decodeBase64(sessionKey), Base64.decodeBase64(decodeUserInfoVO.getIv()));
            if (null == resultByte || resultByte.length == 0) {
                return Response.error(ResponseMessageEnum.NETWORK_ERROR);
            }
            String userInfo = new String(resultByte, StandardCharsets.UTF_8);
            WxUserInfoDTO wxUserInfoDTO = JSON.parseObject(userInfo, WxUserInfoDTO.class);
            log.info("decode wxUserInfoDTO:{}", wxUserInfoDTO);
            User user = updateUserInfo(openId, wxUserInfoDTO);
            return Response.success(user);

        } catch (Exception e) {
            log.error("decodeUserInfo fail decodeUserInfoVO:{}", decodeUserInfoVO, e);
            return Response.error("decodeUserInfo fail");
        }

    }

    private User updateUserInfo(String openId, WxUserInfoDTO wxUserInfoDTO) {
        User user = userDAO.getUserByOpenId(openId);
        if (user == null) {
            user = new User();
            user.setOpenId(openId);
            user.setAvatarUrl(wxUserInfoDTO.getAvatarUrl());
            user.setCity(wxUserInfoDTO.getCity());
            user.setCountry(wxUserInfoDTO.getCountry());
            user.setGender(wxUserInfoDTO.getGender());
            user.setNickName(wxUserInfoDTO.getNickName());
            user.setProvince(wxUserInfoDTO.getProvince());
            user.setUnionId(wxUserInfoDTO.getUnionId());
            userDAO.insertSelective(user);
        } else {
            user.setNickName(wxUserInfoDTO.getNickName());
            user.setAvatarUrl(wxUserInfoDTO.getAvatarUrl());
            user.setCity(wxUserInfoDTO.getCity());
            user.setCountry(wxUserInfoDTO.getCountry());
            user.setGender(wxUserInfoDTO.getGender());
            user.setProvince(wxUserInfoDTO.getProvince());
            user.setUnionId(wxUserInfoDTO.getUnionId());
            userDAO.updateByPrimaryKey(user);
        }
        return user;
    }

    private Code2SessionDTO getSessionKey(String wxCode) {
        RestTemplate client = new RestTemplate();
        Map<String, Object> params = new HashMap<>();
        params.put("appId", wxProperties.getAppId());
        params.put("secret", wxProperties.getSecret());
        params.put("jsCode", wxCode);
        params.put("grantType", wxProperties.getGrantType());
        String res = client.getForObject(wxProperties.getSessionHost() + "?appid={appId}&secret={secret}&js_code={jsCode}&grant_type={grantType}", String.class, params);
        return JSON.parseObject(res, Code2SessionDTO.class);
    }

    @Data
    private static class WxUserInfoDTO {
        private Long userId;
        private Date createTime;
        private String openId;
        private String avatarUrl;
        private String nickName;
        private Integer gender;
        private String city;
        private String language;
        private String province;
        private String country;
        private String unionId;
    }

}
