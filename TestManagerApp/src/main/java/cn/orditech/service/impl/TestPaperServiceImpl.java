
package cn.orditech.service.impl;

import cn.orditech.dao.TestPaperDao;
import cn.orditech.dao.impl.TestPaperDaoImpl;
import cn.orditech.service.TestPaperService;
import cn.orditech.dao.impl.BaseDao;
import cn.orditech.entity.TestPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Service
public class TestPaperServiceImpl extends BaseService<TestPaper> implements TestPaperService{
    @Autowired
    private TestPaperDao testPaperDao;

    protected BaseDao<TestPaper> getDao(){
        return (TestPaperDaoImpl)this.testPaperDao;
    }

    @Override
    public List<TestPaper> findByIds (List<Long> ids) {
        return testPaperDao.findByIds (ids);
    }
}