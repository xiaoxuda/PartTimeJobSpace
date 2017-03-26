
package cn.orditech.service.impl;

import cn.orditech.dao.impl.QuestionDaoImpl;
import cn.orditech.query.QuestionPageQuery;
import cn.orditech.service.QuestionService;
import cn.orditech.dao.impl.BaseDao;
import cn.orditech.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Question> selectByIds (List<Long> ids) {
        return questionDao.selectByIds(ids);
    }

    @Override
    public List<Question> pageQuery (QuestionPageQuery questionPageQuery) {
        return questionDao.pageQuery (questionPageQuery);
    }


}