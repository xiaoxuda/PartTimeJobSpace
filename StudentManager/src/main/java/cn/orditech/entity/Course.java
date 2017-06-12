package cn.orditech.entity;

import java.util.Date;

/**
 * Created by kimi on 2017/6/12.
 */
public class Course implements BaseEntity{
    /**
     * id
     **/
    private Long id;
    /**
     * 课程名称
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

    @Override
    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Date getGmtCreate () {
        return gmtCreate;
    }

    public void setGmtCreate (Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified () {
        return gmtModified;
    }

    public void setGmtModified (Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
