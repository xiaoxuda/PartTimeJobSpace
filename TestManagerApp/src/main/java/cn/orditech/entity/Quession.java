package cn.orditech.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @author kimi
 */
public class Quession implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    
    /** 
     * id 
     **/
    private java.lang.Long id;
    /** 
     * 题型，(1:选择，2:判断) 
     **/
    private java.lang.Boolean type;
    /** 
     * 问题描述 
     **/
    private java.lang.String title;
    /** 
     * 答案集合，json结构，[{code:1,desc:'aaaa'},{code:2,desc:'aaaa'}] 
     **/
    private java.lang.String options;
    /** 
     * 正确答案,对应options中的code 
     **/
    private java.lang.String answer;
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
    
    public void setType(java.lang.Boolean value) {
        this.type = value;
    }
    public java.lang.Boolean getType() {
        return this.type;
    }
    
    public void setTitle(java.lang.String value) {
        this.title = value;
    }
    public java.lang.String getTitle() {
        return this.title;
    }
    
    public void setOptions(java.lang.String value) {
        this.options = value;
    }
    public java.lang.String getOptions() {
        return this.options;
    }
    
    public void setAnswer(java.lang.String value) {
        this.answer = value;
    }
    public java.lang.String getAnswer() {
        return this.answer;
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

