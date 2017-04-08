
package cn.orditech.dao;

import cn.orditech.entity.User;
import java.util.List;

/**
 * 数据操作接口
 * @author kimi
 * @version 1.0
 */
public interface UserDao{
    /**
     * 插入操作
     * @param entity 数据表对应的实体类
     * @return 
     */
    int insert(User entity);
    /**
     * 根据主键删除对应数据
     * @param id
     * @return
     */
    int delete(Long id);
    /**
     * 根据主键更新对应数据条目
     * @param entity
     * @return
     */
    int update(User entity);
    /**
     * 根据主键更新对应数据条目，如果entity数据域为null则不更新对应数据域
     * @param entity
     * @return
     */
    int updateSelective(User entity);
    /**
     * 根据主键查询对应数据条目
     * @param id
     * @return
     */
    User selectOne(Long id);
    /**
     * 根据查询条件返回相应数据条目，entity中值为null的数据域不作为查询条件
     * @param entity
     * @return
     */
    List<User> selectList(User entity);

    /**
     * 通过账号查询用户
     * @param account
     * @return
     */
    User  getUserByAccount(String account);

    /**
     * 通过账号密码查询用户
     * @param account
     * @param pswd
     * @return
     */
    User getUserByAccountAndPassword(String account,String pswd);

    /**
     * 根据部门查询员工ID
     * @param code
     * @return
     */
    List<User> selectUserByDepartment(String code);

    /**
     * 返回所有用户ID
     * @return
     */
    List<Long> selectAllUserIds();
}