
package cn.orditech.service.impl;

import cn.orditech.dao.impl.DepartmentDaoImpl;
import cn.orditech.service.DepartmentService;
import cn.orditech.dao.impl.BaseDao;
import cn.orditech.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Service
public class DepartmentServiceImpl extends BaseService<Department> implements DepartmentService{
    @Autowired
    private DepartmentDaoImpl departmentDao;

    protected BaseDao<Department> getDao(){
        return this.departmentDao;
    }
    
}