package cn.hang.neuq.constant;


/**
 * 常量
 *
 * @author lihang
 */
public class CommonConstant {

    private CommonConstant() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 用户默认头像
     */
    public static final String USER_DEFAULT_AVATAR = "https://s1.ax1x.com/2018/05/19/CcdVQP.png";

    /**
     * 用户正常状态
     */
    public static final Integer USER_STATUS_NORMAL = 0;

    /**
     * 用户禁用状态
     */
    public static final Integer USER_STATUS_LOCK = -1;
    /**
     * 教务系统验证通过
     */
    public static final Integer USER_JW_AUTH_PASS = 0;
    /**
     * 教务系统验证未通过
     */
    public static final Integer USER_JW_AUTH_NOT_PASS = -1;


}
