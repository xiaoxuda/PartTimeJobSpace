package cn.orditech.entity;

import java.util.Date;
/**
 * @author kimi
 */
public class Student implements BaseEntity {
    private static final long serialVersionUID = 5454155825314635342L;
    
    /** 
     * id 
     **/
    private Long id;
    /** 
     * 姓名 
     **/
    private String name;
    /** 
     * 性别 
     **/
    private Integer sex;
    /** 
     * 所在学校 
     **/
    private String school;
    /** 
     * 生日 
     **/
    private String birth;
    /** 
     * 培训机构 
     **/
    private Long orgId;
    /** 
     * 培训机构负责人 
     **/
    private Long referrer;
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
    
    public void setName(String value) {
        this.name = value;
    }
    public String getName() {
        return this.name;
    }
    
    public void setSex(Integer value) {
        this.sex = value;
    }
    public Integer getSex() {
        return this.sex;
    }
    
    public void setSchool(String value) {
        this.school = value;
    }
    public String getSchool() {
        return this.school;
    }
    
    public void setBirth(String value) {
        this.birth = value;
    }
    public String getBirth() {
        return this.birth;
    }
    
    public void setOrgId(Long value) {
        this.orgId = value;
    }
    public Long getOrgId() {
        return this.orgId;
    }

    public Long getReferrer () {
        return referrer;
    }

    public void setReferrer (Long referrer) {
        this.referrer = referrer;
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

