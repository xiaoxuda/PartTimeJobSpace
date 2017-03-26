
package cn.orditech.service;

import cn.orditech.entity.Question;
import cn.orditech.query.QuestionPageQuery;

import java.util.List;

/**
 * 数据操作接口
 * @author kimi
 * @version 1.0
 */
public interface QuestionService{
    /**
     * 插入操作
     * @param entity 数据表对应的实体类
     * @return 
     */
    int insert (Question entity);
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
    int update (Question entity);
    /**
     * 根据主键更新对应数据条目，如果entity数据域为null则不更新对应数据域
     * @param entity
     * @return
     */
    int updateSelective (Question entity);
    /**
     * 根据主键查询对应数据条目
     * @param id
     * @return
     */
    Question selectOne (Long id);
    /**
     * 根据查询条件返回相应数据条目，entity中值为null的数据域不作为查询条件
     * @param entity
     * @return
     */
    List<Question> selectList (Question entity);

    /**
     * 批量查询试题信息
     * @param ids
     * @return
     */
    List<Question> selectByIds(List<Long> ids);

    /**
     * 分页查询试题
     * @param questionPageQuery
     * @return
     */
    List<Question> pageQuery(QuestionPageQuery questionPageQuery);
}