package cn.hang.neuq.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lihang15
 * @description
 * @create 2019-01-22 11:22
 **/
@Data
@Component
@ConfigurationProperties(prefix = "wx")
public class WxProperties {

    private String appId;
    private String secret;
    private String grantType;
    private String sessionHost;

}
