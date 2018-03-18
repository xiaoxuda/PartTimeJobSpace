
package cn.orditech.dao.impl;

import cn.orditech.dao.ScoreDao;
import cn.orditech.entity.Score;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Repository
public class ScoreDaoImpl extends BaseDao<Score> implements ScoreDao{
    @Override
    public void deleteByStudentId (long studentId) {
        getSqlSession ().delete ("deleteByStudentId", studentId);
    }
}