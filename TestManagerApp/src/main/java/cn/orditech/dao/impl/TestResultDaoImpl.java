
package cn.orditech.dao.impl;

import cn.orditech.dao.TestResultDao;
import cn.orditech.entity.TestResult;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Repository
public class TestResultDaoImpl extends BaseDao<TestResult> implements TestResultDao{
    public TestResultDaoImpl(){
        this.namespace="cn.orditech.dao.impl.TestResultDaoImpl";
    }
}