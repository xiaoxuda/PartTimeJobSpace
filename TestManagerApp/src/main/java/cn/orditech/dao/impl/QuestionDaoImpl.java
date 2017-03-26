
package cn.orditech.dao.impl;

import cn.orditech.dao.QuestionDao;
import cn.orditech.entity.Question;
import cn.orditech.query.PageQuery;
import cn.orditech.query.QuestionPageQuery;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author kimi
 * @version 1.0
 */
@Repository
public class QuestionDaoImpl extends BaseDao<Question> implements QuestionDao {
    public QuestionDaoImpl () {
        this.namespace = "cn.orditech.dao.impl.QuestionDaoImpl";
    }

    @Override
    public int delete (Long id) {
        return getSqlSession ().update ("delete", id);
    }

    @Override
    public List<Question> selectByIds (List<Long> ids) {
        if (ids == null || ids.isEmpty ()) {
            return Collections.emptyList ();
        }
        Map<String, Object> param = Maps.newHashMap ();
        param.put ("ids", ids);
        return getSqlSession ().selectList ("selectByIds", param);
    }

    @Override
    public List<Question> pageQuery (QuestionPageQuery questionPageQuery) {
        if (questionPageQuery == null) {
            return Collections.emptyList ();
        }
        Integer pageSize = questionPageQuery.getPageSize ();
        Integer pageNum = questionPageQuery.getPageNum ();
        pageNum = pageNum == null || pageNum <= 0 ? PageQuery.DEFAULT_PAGENUM : pageNum;
        pageSize = pageSize == null || pageSize <= 0 ? PageQuery.DEFAULT_PAGESIZE : pageSize;

        Map<String, Object> param = Maps.newHashMap ();
        param.put ("type", questionPageQuery.getType ());
        param.put ("keyword",questionPageQuery.getKeyword ());
        param.put ("offset", (pageNum-1) * pageSize);
        param.put ("pageSize", pageSize);
        return getSqlSession ().selectList ("pageQuery", param);
    }
}