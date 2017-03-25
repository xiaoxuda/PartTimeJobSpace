
package cn.orditech.dao.impl;

import cn.orditech.dao.QuestionDao;
import cn.orditech.entity.Question;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Repository
public class QuestionDaoImpl extends BaseDao<Question> implements QuestionDao{
    public QuestionDaoImpl(){
        this.namespace="cn.orditech.dao.impl.QuestionDaoImpl";
    }

    @Override
    public int delete(Long id){
        return getSqlSession ().update ("delete",id);
    }
}