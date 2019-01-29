package cn.hang.neuq.interceptor;

import cn.hang.neuq.common.Response;
import cn.hang.neuq.common.ResponseMessageEnum;
import cn.hang.neuq.constant.CommonConstant;
import cn.hang.neuq.dao.JwUserDAO;
import cn.hang.neuq.entity.po.UserJwInfo;
import cn.hang.neuq.util.SessionUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lihang15
 * @description
 * @create 2019-01-24 15:01
 **/
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionUtils sessionUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("utf-8");
        UserJwInfo userJwInfo = sessionUtils.getJwUser();

        if (userJwInfo.getIsJwAuth().equals(CommonConstant.USER_JW_AUTH_NOT_PASS)) {
            response.setHeader("content-type", "application/json;charset=UTF-8");
            response.getWriter().print(JSON.toJSONString(Response.error(ResponseMessageEnum.JW_NOT_AUTH_PASS)));
            return false;
        }
        return true;
    }
}

