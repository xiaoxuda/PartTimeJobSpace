
package cn.orditech.dao.impl;

import cn.orditech.dao.UserDao;
import cn.orditech.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao{
    public UserDaoImpl(){
        this.namespace="cn.orditech.dao.impl.UserDaoImpl";
    }
}