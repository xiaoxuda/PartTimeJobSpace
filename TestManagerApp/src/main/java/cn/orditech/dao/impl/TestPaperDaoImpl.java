
package cn.orditech.dao.impl;

import cn.orditech.dao.TestPaperDao;
import cn.orditech.entity.TestPaper;
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
public class TestPaperDaoImpl extends BaseDao<TestPaper> implements TestPaperDao{
    public TestPaperDaoImpl(){
        this.namespace="cn.orditech.dao.impl.TestPaperDaoImpl";
    }

    @Override
    public int delete(Long id){
        return getSqlSession ().update ("delete",id);
    }

    @Override
    public List<TestPaper> findByIds (List<Long> ids) {
        Map<String,List<Long>> param = Maps.newHashMap ();
        param.put ("ids",ids);
        return getSqlSession ().selectList ("findByIds",param);
    }
}