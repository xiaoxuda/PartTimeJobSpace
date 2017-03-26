package cn.orditech.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @author kimi
 */
public class TestPaper implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    
    /** 
     * id 
     **/
    private Long id;
    /** 
     * 试卷描述 
     **/
    private String title;
    /** 
     * 试题集合，支持分数定制，json结构[{id:1,score:2},{id:2,score:2}] 
     **/
    private String questions;
    /** 
     * 分数 
     **/
    private Integer score;
    /** 
     * 是否已删除，0：否，1：是 
     **/
    private Boolean deleted;
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
    
    public void setTitle(String value) {
        this.title = value;
    }
    public String getTitle() {
        return this.title;
    }

    public String getQuestions () {
        return questions;
    }

    public void setQuestions (String questions) {
        this.questions = questions;
    }

    public void setScore(Integer value) {
        this.score = value;
    }
    public Integer getScore() {
        return this.score;
    }
    
    public void setDeleted(Boolean value) {
        this.deleted = value;
    }
    public Boolean getDeleted() {
        return this.deleted;
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

