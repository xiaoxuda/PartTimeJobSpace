
package cn.orditech.service;

import cn.orditech.entity.TestResult;
import java.util.List;

/**
 * 数据操作接口
 * @author kimi
 * @version 1.0
 */
public interface TestResultService{
    /**
     * 插入操作
     * @param entity 数据表对应的实体类
     * @return 
     */
    int insert(TestResult entity);
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
    int update(TestResult entity);
    /**
     * 根据主键更新对应数据条目，如果entity数据域为null则不更新对应数据域
     * @param entity
     * @return
     */
    int updateSelective(TestResult entity);
    /**
     * 根据主键查询对应数据条目
     * @param id
     * @return
     */
    TestResult selectOne(Long id);
    /**
     * 根据查询条件返回相应数据条目，entity中值为null的数据域不作为查询条件
     * @param entity
     * @return
     */
    List<TestResult> selectList(TestResult entity);

    /**
     * 批量查询跟定用户的考试结果
     * @param userIds
     * @return
     */
    List<TestResult> selectListByUserIds(List<Long> userIds);
}