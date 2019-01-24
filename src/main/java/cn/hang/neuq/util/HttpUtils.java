package cn.hang.neuq.util;

import cn.hang.neuq.constant.ApiConstant;
import cn.hang.neuq.entity.dto.JwApiResDTO;
import cn.hang.neuq.exception.InfoException;
import cn.hang.neuq.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lihang15
 * @description
 * @create 2019-01-24 19:46
 **/
@Component
public class HttpUtils {

    @Autowired
    private AuthService authService;

    @Autowired
    private SessionUtils sessionUtils;

    public void handleJwResponse(JwApiResDTO jwApiResDTO) {

        if (jwApiResDTO == null) {
            throw new InfoException(ApiConstant.JW_NETWORK_ERROR_MESSAGE);
        }
        if (jwApiResDTO.getCode() != null && jwApiResDTO.getCode().equals(ApiConstant.JW_NOT_AUTH_PASS_CODE)) {
            authService.jwNotAuthResponse(sessionUtils.getUserId());
        }
        if (jwApiResDTO.getCode() != null && !jwApiResDTO.getCode().equals(ApiConstant.JW_SUCCESS)) {
            throw new InfoException(ApiConstant.JW_NETWORK_ERROR_MESSAGE);
        }
    }

}
