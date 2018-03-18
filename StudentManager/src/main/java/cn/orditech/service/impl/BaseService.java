package cn.orditech.service.impl;

import cn.orditech.entity.BaseEntity;
import java.util.List;
import cn.orditech.dao.impl.BaseDao;

/**
 * 服务基础类
 * @author kimi
 * @version 1.0
 */
public abstract class BaseService<T extends BaseEntity> {
    protected abstract BaseDao<T> getDao();
    /**
     * 插入操作
     * @param entity 数据表对应的实体类
     * @return id
     */
    public long insert(T entity){
        return this.getDao().insert(entity);
    }
    /**
     * 根据主键删除对应数据
     * @param id
     * @return
     */
    public int delete(Long id){
        return this.getDao().delete(id);
    }
    /**
     * 根据主键更新对应数据条目
     * @param entity
     * @return
     */
    public int updateById(T entity){
        return this.getDao().updateById (entity);
    }
    /**
     * 根据主键更新对应数据条目，如果entity数据域为null则不更新对应数据域
     * @param entity
     * @return
     */
    public int updateSelectiveById(T entity){
        return this.getDao().updateSelectiveById (entity);
    }
    /**
     * 根据主键查询对应数据条目
     * @param id
     * @return
     */
    public T selectOne(Long id){
        return this.getDao().selectOne(id);
    }
    /**
     * 根据查询条件返回相应数据条目，entity中值为null的数据域不作为查询条件
     * @param entity
     * @return
     */
    public List<T> selectList(T entity){
        return this.getDao().selectList(entity);
    }
}
