
package cn.orditech.service.impl;

import cn.orditech.dao.impl.QuestionDaoImpl;
import cn.orditech.service.QuestionService;
import cn.orditech.dao.impl.BaseDao;
import cn.orditech.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Service
public class QuestionServiceImpl extends BaseService<Question> implements QuestionService{
    @Autowired
    private QuestionDaoImpl questionDao;

    protected BaseDao<Question> getDao(){
        return this.questionDao;
    }
    
}