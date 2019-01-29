package cn.hang.neuq.entity.po;

import java.io.Serializable;
import java.util.Date;

/**
 * gpa
 * @author 
 */
public class Gpa implements Serializable {
    private Long id;

    private Long userId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 分数
     */
    private String score;

    /**
     * 绩点
     */
    private Float gpa;

    /**
     * 学分
     */
    private Float credit;

    /**
     * 班级ID
     */
    private String classId;

    private String studentId;

    /**
     * 学期
     */
    private String semester;

    /**
     * 成绩类型
     */
    private String examType;

    private String isExamInvalid;

    private Integer status;

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Float getGpa() {
        return gpa;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    public Float getCredit() {
        return credit;
    }

    public void setCredit(Float credit) {
        this.credit = credit;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getIsExamInvalid() {
        return isExamInvalid;
    }

    public void setIsExamInvalid(String isExamInvalid) {
        this.isExamInvalid = isExamInvalid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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