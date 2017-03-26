
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

    public User  getUserByAccount(String account){
        return sqlSession.selectOne(this.namespace+".getUserByAccount", account);
    }

    public User getUserByAccountAndPassword(String account,String pswd){
        User user = new User();
        user.setAccount(account);
        user.setPassword(pswd);
        return sqlSession.selectOne(this.namespace+".getUserByAccountAndPassword",user );
    }
}