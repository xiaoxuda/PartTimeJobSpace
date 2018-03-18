
package cn.orditech.dao;

import cn.orditech.entity.Student;
import java.util.List;

/**
 * 数据操作接口
 * @author kimi
 * @version 1.0
 */
public interface StudentDao{
    /**
     * 插入操作
     * @param entity 数据表对应的实体类
     * @return id
     */
    long insert (Student entity);
    /**
     * 根据主键删除对应数据
     * @param id
     * @return
     */
    int delete (Long id);
    /**
     * 根据主键更新对应数据条目
     * @param entity
     * @return
     */
    int updateById (Student entity);
    /**
     * 根据主键更新对应数据条目，如果entity数据域为null则不更新对应数据域
     * @param entity
     * @return
     */
    int updateSelectiveById (Student entity);
    /**
     * 根据主键查询对应数据条目
     * @param id
     * @return
     */
    Student selectOne (Long id);
    /**
     * 根据查询条件返回相应数据条目，entity中值为null的数据域不作为查询条件
     * @param entity
     * @return
     */
    List<Student> selectList (Student entity);
}