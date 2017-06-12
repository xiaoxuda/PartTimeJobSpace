package cn.orditech.entity;

import java.util.Date;
/**
 * @author kimi
 */
public class Organization implements BaseEntity {
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

