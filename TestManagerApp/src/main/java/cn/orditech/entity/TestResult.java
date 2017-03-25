package cn.orditech.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @author kimi
 */
public class TestResult implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    
    /** 
     * id 
     **/
    private java.lang.Long id;
    /** 
     * 用户ID 
     **/
    private java.lang.Long userId;
    /** 
     * 试卷ID 
     **/
    private java.lang.Long testId;
    /** 
     * 答案，json结构，[{id:1,answer:''}] 
     **/
    private java.lang.String answer;
    /** 
     * 得分 
     **/
    private java.lang.Integer score;
    /** 
     * gmtCreate 
     **/
    private Date gmtCreate;
    /** 
     * gmtModified 
     **/
    private Date gmtModified;

    public void setId(java.lang.Long value) {
        this.id = value;
    }
    public java.lang.Long getId() {
        return this.id;
    }
    
    public void setUserId(java.lang.Long value) {
        this.userId = value;
    }
    public java.lang.Long getUserId() {
        return this.userId;
    }
    
    public void setTestId(java.lang.Long value) {
        this.testId = value;
    }
    public java.lang.Long getTestId() {
        return this.testId;
    }
    
    public void setAnswer(java.lang.String value) {
        this.answer = value;
    }
    public java.lang.String getAnswer() {
        return this.answer;
    }
    
    public void setScore(java.lang.Integer value) {
        this.score = value;
    }
    public java.lang.Integer getScore() {
        return this.score;
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

