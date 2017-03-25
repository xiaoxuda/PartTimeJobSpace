
package cn.orditech.service.impl;

import cn.orditech.dao.impl.UserDaoImpl;
import cn.orditech.service.UserService;
import cn.orditech.dao.impl.BaseDao;
import cn.orditech.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService{
    @Autowired
    private UserDaoImpl userDao;

    protected BaseDao<User> getDao(){
        return this.userDao;
    }
    
}