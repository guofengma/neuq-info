package cn.hang.neuq.constant;

/**
 * @author lihang15
 * @description
 * @create 2019-01-22 13:07
 **/
public class CacheConstant {
    private CacheConstant() {
        throw new IllegalStateException("Utility class");
    }
    public static final String ACCESS_TOKEN = "info_access_token:%s";
    public static final String USER_ACCESS_TOKEN = "info_user_access_token:%s";
    public static final String SESSION_JW_USER_INFO = "info_session_jw_user_info";
}
