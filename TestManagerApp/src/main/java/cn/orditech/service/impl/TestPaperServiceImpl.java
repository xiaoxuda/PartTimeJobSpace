
package cn.orditech.service.impl;

import cn.orditech.dao.impl.TestPaperDaoImpl;
import cn.orditech.service.TestPaperService;
import cn.orditech.dao.impl.BaseDao;
import cn.orditech.entity.TestPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Service
public class TestPaperServiceImpl extends BaseService<TestPaper> implements TestPaperService{
    @Autowired
    private TestPaperDaoImpl testPaperDao;

    protected BaseDao<TestPaper> getDao(){
        return this.testPaperDao;
    }
    
}