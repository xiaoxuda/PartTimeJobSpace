
package cn.orditech.dao.impl;

import cn.orditech.dao.TestPaperDao;
import cn.orditech.entity.TestPaper;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Repository
public class TestPaperDaoImpl extends BaseDao<TestPaper> implements TestPaperDao{
    public TestPaperDaoImpl(){
        this.namespace="cn.orditech.dao.impl.TestPaperDaoImpl";
    }
}