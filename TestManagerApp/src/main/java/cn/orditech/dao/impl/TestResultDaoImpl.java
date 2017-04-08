
package cn.orditech.dao.impl;

import cn.orditech.dao.TestResultDao;
import cn.orditech.entity.TestResult;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Repository
public class TestResultDaoImpl extends BaseDao<TestResult> implements TestResultDao{

    @Override
    public List<TestResult> selectListByUserIds (List<Long> userIds) {
        Map<String,List<Long>> paramMap = Maps.newHashMap ();
        paramMap.put("userIds",userIds);
        return getSqlSession ().selectList (getFullStatement ("selectListByUserIds"),paramMap);
    }
}