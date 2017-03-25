package cn.orditech.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @author kimi
 */
public class User implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;
    
    /** 
     * id 
     **/
    private java.lang.Long id;
    /** 
     * 账号 
     **/
    private java.lang.String acount;
    /** 
     * 密码 
     **/
    private java.lang.String password;
    /** 
     * 姓名 
     **/
    private java.lang.String name;
    /** 
     * 性别（1：男，2：女） 
     **/
    private java.lang.Boolean sex;
    /** 
     * 所属部门编号 
     **/
    private java.lang.String department;
    /** 
     * 用户类型（1：普通用户，2：管理员） 
     **/
    private java.lang.Boolean type;
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
    
    public void setAcount(java.lang.String value) {
        this.acount = value;
    }
    public java.lang.String getAcount() {
        return this.acount;
    }
    
    public void setPassword(java.lang.String value) {
        this.password = value;
    }
    public java.lang.String getPassword() {
        return this.password;
    }
    
    public void setName(java.lang.String value) {
        this.name = value;
    }
    public java.lang.String getName() {
        return this.name;
    }
    
    public void setSex(java.lang.Boolean value) {
        this.sex = value;
    }
    public java.lang.Boolean getSex() {
        return this.sex;
    }
    
    public void setDepartment(java.lang.String value) {
        this.department = value;
    }
    public java.lang.String getDepartment() {
        return this.department;
    }
    
    public void setType(java.lang.Boolean value) {
        this.type = value;
    }
    public java.lang.Boolean getType() {
        return this.type;
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
