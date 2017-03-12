
package cn.orditech.dao.impl;

import cn.orditech.dao.QuessionDao;
import cn.orditech.entity.Quession;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Repository
public class QuessionDaoImpl extends BaseDao<Quession> implements QuessionDao{
    public QuessionDaoImpl(){
        this.namespace="cn.orditech.dao.impl.QuessionDaoImpl";
    }
}