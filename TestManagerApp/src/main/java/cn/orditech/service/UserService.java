
package cn.orditech.service;

import cn.orditech.entity.User;
import java.util.List;
import java.util.Map;

/**
 * 数据操作接口
 * @author kimi
 * @version 1.0
 */
public interface UserService{
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
     * 通过账户名获取账户信息
     * @param account
     * @return
     */
    User getUserByAccount(String account);

    /**
     * 用户登录时，验证用户名、密码是否正确
     * @param account
     * @param password
     * @return
     */
    User getUserByAccountAndPassword(String account,String password);

    /**
     * 用户注册
     * @param user
     * @return
     */
    Map<String,Object> register(User user);

    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    Map<String,Object> login(String account,String password);

    /**
     *退出登录
     * @param ticket
     * @param status
     */
    void logout(String ticket,int status);

    /**
     * 按部门查询所有用户ID
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