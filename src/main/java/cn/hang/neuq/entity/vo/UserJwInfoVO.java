package cn.hang.neuq.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user_jw_info
 * @author 
 */
@Data
public class UserJwInfoVO implements Serializable {

    private Long userId;

    /**
     * 班级号
     */
    private String classId;

    /**
     * 学号
     */
    private String studentId;

    private String studentName;

    /**
     * 年级：如2015级
     */
    private String grade;

    /**
     * 专业
     */
    private String profession;

    /**
     * 学院
     */
    private String college;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 加权平均绩点
     */
    private Float weightAverageGpa;

    private String jwUsername;

    /**
     * 是否教务认证
     */
    private Integer isJwAuth;

    private Date gmtCreate;

    private Date gmtModified;

    private static final long serialVersionUID = 1L;
}