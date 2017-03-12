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
    private java.lang.Long id;
    /** 
     * 试卷描述 
     **/
    private java.lang.String title;
    /** 
     * 试题集合，支持分数定制，json结构[{id:1,score:2},{id:2,score:2}] 
     **/
    private java.lang.String quessions;
    /** 
     * 分数 
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
    
    public void setTitle(java.lang.String value) {
        this.title = value;
    }
    public java.lang.String getTitle() {
        return this.title;
    }
    
    public void setQuessions(java.lang.String value) {
        this.quessions = value;
    }
    public java.lang.String getQuessions() {
        return this.quessions;
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

