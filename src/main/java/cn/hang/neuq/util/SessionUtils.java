package cn.hang.neuq.util;

import cn.hang.neuq.constant.CacheConstant;
import cn.hang.neuq.constant.CommonConstant;
import cn.hang.neuq.constant.SecurityConstant;
import cn.hang.neuq.dao.JwUserDAO;
import cn.hang.neuq.dao.UserDAO;
import cn.hang.neuq.entity.po.UserJwInfo;
import cn.hang.neuq.exception.InfoException;
import cn.hang.neuq.exception.TokenExpiredException;
import cn.hang.neuq.exception.TokenInvalidException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lihang15
 * @description
 * @create 2019-01-22 15:37
 **/
@Component
public class SessionUtils {

    ThreadLocal<UserJwInfo> userJwInfoThreadLocal = new ThreadLocal<>();

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JwUserDAO jwUserDAO;

    public static HttpServletRequest currentRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attrs.getRequest();
    }

    public String getOpenId() {
        String[] array = this.getTokenArray();
        return array[0];
    }


    public String getSessionKey() {
        String[] array = this.getTokenArray();
        return array[1];
    }

    public Long getUserId() {
        String[] array = this.getTokenArray();
        if (array[2] != null) {
            return Long.parseLong(array[2]);
        } else {
            throw new TokenInvalidException();
        }
    }

    public UserJwInfo getJwUser() {

        UserJwInfo userJwInfo = userJwInfoThreadLocal.get();
        if (userJwInfo != null) {
            return userJwInfo;
        }
        Long userId = this.getUserId();
        userJwInfo = jwUserDAO.getInfoByUserId(userId);
        userJwInfoThreadLocal.set(userJwInfo);
        return userJwInfo;
    }


    private String[] getTokenArray() {
        String accessToken = currentRequest().getHeader(SecurityConstant.HEADER);
        if (StringUtils.isBlank(accessToken)) {
            throw new TokenInvalidException();
        }
        String value = redisTemplate.opsForValue().get(String.format(CacheConstant.ACCESS_TOKEN, accessToken));
        if (StringUtils.isBlank(value)) {
            throw new TokenExpiredException();
        }
        String[] array = value.split("#");
        if (array.length == 3) {
            return array;
        } else {
            throw new TokenInvalidException();
        }
    }


}
