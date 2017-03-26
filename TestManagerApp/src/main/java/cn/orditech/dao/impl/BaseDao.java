package cn.orditech.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 数据库操作基类,T数据实体类
 * @author kimi
 * @version 1.0
 */
public class BaseDao<T>{
    protected String namespace;
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
    public int insert(T entity){
        return sqlSession.insert(this.namespace+".insert", entity);
    };
    /**
     * 根据主键删除对应数据
     * @param id
     * @return
     */
    public int delete(Long id){
        return sqlSession.delete(this.namespace+".delete", id);
    };
    /**
     * 根据主键更新对应数据条目
     * @param entity
     * @return
     */
    public int update(T entity){
        return sqlSession.update(this.namespace+".update", entity);
    };
    /**
     * 根据主键更新对应数据条目，如果entity数据域为null则不更新对应数据域
     * @param entity
     * @return
     */
    public int updateSelective(T entity){
        return sqlSession.update(this.namespace+".updateSelective", entity);
    };
    /**
     * 根据主键查询对应数据条目
     * @param id
     * @return
     */
    public T selectOne(Long id){
        return sqlSession.selectOne(this.namespace+".selectOne", id);
    };
    /**
     * 根据查询条件返回相应数据条目，entity中值为null的数据域不作为查询条件
     * @param entity
     * @return
     */
    public List<T> selectList(T entity){
        return sqlSession.selectList(this.namespace+".selectList", entity);
    };
}
