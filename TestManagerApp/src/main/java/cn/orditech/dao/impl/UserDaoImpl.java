
package cn.orditech.dao.impl;

import cn.orditech.dao.UserDao;
import cn.orditech.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao{

    @Override
    public User  getUserByAccount(String account){
        return sqlSession.selectOne(getFullStatement("getUserByAccount"), account);
    }

    @Override
    public User getUserByAccountAndPassword(String account,String pswd){
        User user = new User();
        user.setAccount(account);
        user.setPassword(pswd);
        return sqlSession.selectOne(getFullStatement("getUserByAccountAndPassword"),user );
    }

    @Override
    public List<User> selectUserByDepartment (String code) {
        return getSqlSession ().selectList (getFullStatement ("selectUserByDepartment"),code);
    }

    @Override
    public List<Long> selectAllUserIds () {
        return getSqlSession ().selectList (getFullStatement ("selectAllUserIds"));
    }
}