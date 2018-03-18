
package cn.orditech.service;

import cn.orditech.entity.Course;

import java.util.List;

/**
 * 数据操作接口
 * @author kimi
 * @version 1.0
 */
public interface CourseService{
    /**
     * 插入操作
     * @param entity 数据表对应的实体类
     * @return id
     */
    long insert (Course entity);
    /**
     * 根据主键删除对应数据
     * @param id
     * @return
     */
    int delete (Long id);
    /**
     * 根据主键更新对应数据条目
     * @param entity
     * @return id
     */
    int updateById (Course entity);
    /**
     * 根据主键更新对应数据条目，如果entity数据域为null则不更新对应数据域
     * @param entity
     * @return id
     */
    int updateSelectiveById (Course entity);
    /**
     * 根据主键查询对应数据条目
     * @param id
     * @return
     */
    Course selectOne (Long id);
    /**
     * 根据查询条件返回相应数据条目，entity中值为null的数据域不作为查询条件
     * @param entity
     * @return
     */
    List<Course> selectList (Course entity);
}