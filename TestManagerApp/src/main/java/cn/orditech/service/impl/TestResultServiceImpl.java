
package cn.orditech.service.impl;

import cn.orditech.dao.TestResultDao;
import cn.orditech.dao.impl.TestResultDaoImpl;
import cn.orditech.service.TestResultService;
import cn.orditech.dao.impl.BaseDao;
import cn.orditech.entity.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Service
public class TestResultServiceImpl extends BaseService<TestResult> implements TestResultService{
    @Autowired
    private TestResultDao testResultDao;

    protected BaseDao<TestResult> getDao(){
        return (TestResultDaoImpl)this.testResultDao;
    }

    @Override
    public List<TestResult> selectListByUserIds (List<Long> userIds) {
        return testResultDao.selectListByUserIds(userIds);
    }
}