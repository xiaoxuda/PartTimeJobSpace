
package cn.orditech.service.impl;

import cn.orditech.dao.impl.QuessionDaoImpl;
import cn.orditech.service.QuessionService;
import cn.orditech.dao.impl.BaseDao;
import cn.orditech.entity.Quession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Service
public class QuessionServiceImpl extends BaseService<Quession> implements QuessionService{
    @Autowired
    private QuessionDaoImpl quessionDao;

    protected BaseDao<Quession> getDao(){
        return this.quessionDao;
    }
    
}