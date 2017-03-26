package cn.orditech.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @author kimi
 */
public class Question implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    
    /** 
     * id 
     **/
    private Long id;
    /** 
     * 题型，(1:选择，2:判断) 
     **/
    private Integer type;
    /** 
     * 问题描述 
     **/
    private String title;
    /** 
     * 答案集合，json结构，[{code:1,desc:'aaaa'},{code:2,desc:'aaaa'}] 
     **/
    private String options;
    /** 
     * 正确答案,对应options中的code 
     **/
    private String answer;
    /** 
     * 默认分数 
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
    
    public void setType(Integer value) {
        this.type = value;
    }
    public Integer getType() {
        return this.type;
    }
    
    public void setTitle(String value) {
        this.title = value;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setOptions(String value) {
        this.options = value;
    }
    public String getOptions() {
        return this.options;
    }
    
    public void setAnswer(String value) {
        this.answer = value;
    }
    public String getAnswer() {
        return this.answer;
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

