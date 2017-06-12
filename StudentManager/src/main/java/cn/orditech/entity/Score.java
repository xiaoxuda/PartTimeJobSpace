package cn.orditech.entity;

import java.util.Date;
/**
 * @author kimi
 */
public class Score implements BaseEntity {
    private static final long serialVersionUID = 5454155825314635342L;
    
    /** 
     * id 
     **/
    private Long id;
    /** 
     * 学生编码 
     **/
    private Long studentId;
    /** 
     * 培训机构 
     **/
    private Long orgId;
    /** 
     * 课程 
     **/
    private Long course;
    /** 
     * 得分 
     **/
    private Integer score;
    /** 
     * 测试时间 
     **/
    private String testDate;
    /** 
     * gmtCreate 
     **/
    private Date gmtCreate;
    /** 
     * gmtModified 
     **/
    private Date gmtModified;

    public void setId(Long value) {
        this.id = value;
    }
    public Long getId() {
        return this.id;
    }
    
    public void setStudentId(Long value) {
        this.studentId = value;
    }
    public Long getStudentId() {
        return this.studentId;
    }
    
    public void setOrgId(Long value) {
        this.orgId = value;
    }
    public Long getOrgId() {
        return this.orgId;
    }
    
    public void setCourse(Long value) {
        this.course = value;
    }
    public Long getCourse() {
        return this.course;
    }
    
    public void setScore(Integer value) {
        this.score = value;
    }
    public Integer getScore() {
        return this.score;
    }

    public String getTestDate () {
        return testDate;
    }

    public void setTestDate (String testDate) {
        this.testDate = testDate;
    }

    public void setGmtCreate(Date value) {
        this.gmtCreate = value;
    }
    public Date getGmtCreate() {
        return this.gmtCreate;
    }
    
    public void setGmtModified(Date value) {
        this.gmtModified = value;
    }
    public Date getGmtModified() {
        return this.gmtModified;
    }
    
    
}

