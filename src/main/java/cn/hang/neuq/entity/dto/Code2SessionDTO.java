package cn.hang.neuq.entity.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
 *
 * @author lihang15
 * @description
 * @create 2019-01-22 11:45
 **/
@Data
public class Code2SessionDTO {
    private String openid;
    @JSONField(name = "session_key")
    private String sessionKey;
    private String unionid;
    private Integer errcode;
    private String errmsg;


}
