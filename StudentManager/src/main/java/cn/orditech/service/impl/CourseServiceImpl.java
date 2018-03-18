
package cn.orditech.service.impl;

import cn.orditech.dao.CourseDao;
import cn.orditech.dao.impl.CourseDaoImpl;
import cn.orditech.service.CourseService;
import cn.orditech.dao.impl.BaseDao;
import cn.orditech.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Service
public class CourseServiceImpl extends BaseService<Course> implements CourseService{
    @Autowired
    private CourseDao courceDao;

    protected BaseDao<Course> getDao(){
        return (CourseDaoImpl)this.courceDao;
    }
    
}