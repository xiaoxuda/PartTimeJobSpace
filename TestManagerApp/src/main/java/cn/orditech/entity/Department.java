package cn.orditech.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @author kimi
 */
public class Department implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    
    /** 
     * id 
     **/
    private java.lang.Long id;
    /** 
     * 部门编号 
     **/
    private java.lang.String code;
    /** 
     * 部门名称 
     **/
    private java.lang.String name;
    /** 
     * 创建时间 
     **/
    private Date gmtCreate;
    /** 
     * 更新时间 
     **/
    private Date gmtModified;

    public void setId(java.lang.Long value) {
        this.id = value;
    }
    public java.lang.Long getId() {
        return this.id;
    }
    
    public void setCode(java.lang.String value) {
        this.code = value;
    }
    public java.lang.String getCode() {
        return this.code;
    }
    
    public void setName(java.lang.String value) {
        this.name = value;
    }
    public java.lang.String getName() {
        return this.name;
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

