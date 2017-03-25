
package cn.orditech.service.impl;

import cn.orditech.dao.impl.TestResultDaoImpl;
import cn.orditech.service.TestResultService;
import cn.orditech.dao.impl.BaseDao;
import cn.orditech.entity.TestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Service
public class TestResultServiceImpl extends BaseService<TestResult> implements TestResultService{
    @Autowired
    private TestResultDaoImpl testResultDao;

    protected BaseDao<TestResult> getDao(){
        return this.testResultDao;
    }
    
}