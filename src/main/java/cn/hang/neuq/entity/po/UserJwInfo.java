package cn.hang.neuq.entity.po;

import java.io.Serializable;
import java.util.Date;

/**
 * user_jw_info
 *
 * @author
 */
public class UserJwInfo implements Serializable {
    public UserJwInfo() {
    }

    public UserJwInfo(Long id, Float weightAverageGpa) {
        this.id = id;
        this.weightAverageGpa = weightAverageGpa;
    }

    private Long id;

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

    private String jwPassword;

    /**
     * 是否教务认证
     */
    private Integer isJwAuth;

    private Date gmtCreate;

    private Date gmtModified;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Float getWeightAverageGpa() {
        return weightAverageGpa;
    }

    public void setWeightAverageGpa(Float weightAverageGpa) {
        this.weightAverageGpa = weightAverageGpa;
    }

    public String getJwUsername() {
        return jwUsername;
    }

    public void setJwUsername(String jwUsername) {
        this.jwUsername = jwUsername;
    }

    public String getJwPassword() {
        return jwPassword;
    }

    public void setJwPassword(String jwPassword) {
        this.jwPassword = jwPassword;
    }

    public Integer getIsJwAuth() {
        return isJwAuth;
    }

    public void setIsJwAuth(Integer isJwAuth) {
        this.isJwAuth = isJwAuth;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}