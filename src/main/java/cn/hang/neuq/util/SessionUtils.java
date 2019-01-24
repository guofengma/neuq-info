package cn.hang.neuq.util;

import cn.hang.neuq.constant.CacheConstant;
import cn.hang.neuq.constant.SecurityConstant;
import cn.hang.neuq.exception.InfoException;
import cn.hang.neuq.exception.TokenInvalidException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lihang15
 * @description
 * @create 2019-01-22 15:37
 **/
@Component
public class SessionUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public static HttpServletRequest currentRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attrs.getRequest();
    }

    public String getOpenId() {
        String accessToken = currentRequest().getHeader(SecurityConstant.HEADER);
        if (StringUtils.isBlank(accessToken)) {
            throw new TokenInvalidException("accessToken为空");
        }
        String value = redisTemplate.opsForValue().get(CacheConstant.ACCESS_TOKEN + accessToken);
        String[] array = new String[0];
        if (value != null) {
            array = value.split("#");
        }
        if (array.length == 3) {
            return array[0];
        }
        return "";

    }

    public String getSessionKey() {
        String accessToken = currentRequest().getHeader(SecurityConstant.HEADER);
        if (StringUtils.isBlank(accessToken)) {
            throw new TokenInvalidException("accessToken为空");
        }
        String value = redisTemplate.opsForValue().get(CacheConstant.ACCESS_TOKEN + accessToken);
        String[] array = new String[0];
        if (value != null) {
            array = value.split("#");
        }
        if (array.length == 3) {
            return array[1];
        }
        return "";
    }

    public Long getUserId() {
        String accessToken = currentRequest().getHeader(SecurityConstant.HEADER);
        if (StringUtils.isBlank(accessToken)) {
            throw new TokenInvalidException("accessToken为空");
        }
        String value = redisTemplate.opsForValue().get(CacheConstant.ACCESS_TOKEN + accessToken);
        String[] array = new String[0];
        if (value != null) {
            array = value.split("#");
        }
        if (array.length == 3) {
            return Long.parseLong(array[2]);
        }
        return null;
    }

}
