package cn.hang.neuq.constant;

/**
 * @author lihang15
 * @description
 * @create 2019-01-22 13:07
 **/
public class ApiConstant {
    private ApiConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final Integer JW_SUCCESS = 0;
    public static final Integer JW_NOT_AUTH_PASS_CODE = 401;

    public static final Integer JW_NETWORK_ERROR_CODE = 400;

    public static final String JW_NETWORK_ERROR_MESSAGE = "教务系统异常，请稍后重试";



}
