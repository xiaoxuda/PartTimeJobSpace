
package cn.orditech.service.impl;

import cn.orditech.dao.ScoreDao;
import cn.orditech.dao.impl.ScoreDaoImpl;
import cn.orditech.service.ScoreService;
import cn.orditech.dao.impl.BaseDao;
import cn.orditech.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Service
public class ScoreServiceImpl extends BaseService<Score> implements ScoreService{
    @Autowired
    private ScoreDao scoreDao;

    protected BaseDao<Score> getDao(){
        return (ScoreDaoImpl)this.scoreDao;
    }

    @Override
    public void deleteByStudentId (long studentId) {
        scoreDao.deleteByStudentId (studentId);
    }
}