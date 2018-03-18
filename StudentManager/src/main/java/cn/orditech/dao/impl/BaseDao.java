package cn.orditech.dao.impl;

import cn.orditech.entity.BaseEntity;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 数据库操作基类,T数据实体类
 * @author kimi
 * @version 1.0
 */
public class BaseDao<T extends BaseEntity>{
    @Autowired
    protected SqlSessionTemplate sqlSession;
    /**
     * 返回sqlSession
     * @return
     */
    protected SqlSessionTemplate getSqlSession(){
        return this.sqlSession;
    }
    /**
     * 用于自动注入
     * @param sqlSession
     */
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }
    /**
     * 插入操作
     * @param entity 数据表对应的实体类
     * @return 
     */
    public long insert(T entity){
        sqlSession.insert(getFullStatement("insert"), entity);
        return entity.getId ();
    }
    /**
     * 根据主键删除对应数据
     * @param id
     * @return
     */
    public int delete(Long id){
        return sqlSession.delete(getFullStatement("delete"), id);
    }
    /**
     * 根据主键更新对应数据条目
     * @param entity
     * @return
     */
    public int updateById(T entity){
        return sqlSession.update(getFullStatement("updateById"), entity);
    }
    /**
     * 根据主键更新对应数据条目，如果entity数据域为null则不更新对应数据域
     * @param entity
     * @return
     */
    public int updateSelectiveById(T entity){
        return sqlSession.update(getFullStatement("updateSelectiveById"), entity);
    }
    /**
     * 根据主键查询对应数据条目
     * @param id
     * @return
     */
    public T selectOne(Long id){
        return sqlSession.selectOne(getFullStatement("selectOne"), id);
    }
    /**
     * 根据查询条件返回相应数据条目，entity中值为null的数据域不作为查询条件
     * @param entity
     * @return
     */
    public List<T> selectList(T entity){
        return sqlSession.selectList(getFullStatement("selectList"), entity);
    }

    /**
     * 获取访问链接
     * @param key
     * @return
     */
    public String getFullStatement(String key){
        return this.getClass ().getName ()+"."+key;
    }
}
