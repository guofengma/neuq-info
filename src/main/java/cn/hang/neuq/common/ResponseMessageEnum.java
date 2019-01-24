package cn.hang.neuq.common;


/**
 * @author lihang15
 * @description
 * @create 2018-11-27 19:38
 **/
public enum ResponseMessageEnum implements ResponseMessageInterface {
    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),
    JW_NOT_AUTH_PASS(401, "教务系统未认证"),
    ACCESS_TOKEN_NOT_EXIST(402, "accessToken过期"),
    ACCESS_TOKEN_INVALID(403, "accessToken无效"),
    ERROR(500, "操作失败"),
    NETWORK_ERROR(501, "服务器开小差了"),
    JW_NETWORK_ERROR(502, "服务器开小差了")
    ;

    private int code;
    private String message;

    ResponseMessageEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
