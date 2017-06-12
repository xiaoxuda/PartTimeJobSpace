
package cn.orditech.service.impl;

import cn.orditech.dao.StudentDao;
import cn.orditech.dao.impl.StudentDaoImpl;
import cn.orditech.service.StudentService;
import cn.orditech.dao.impl.BaseDao;
import cn.orditech.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Service
public class StudentServiceImpl extends BaseService<Student> implements StudentService{
    @Autowired
    private StudentDao studentDao;

    protected BaseDao<Student> getDao(){
        return (StudentDaoImpl)this.studentDao;
    }
    
}