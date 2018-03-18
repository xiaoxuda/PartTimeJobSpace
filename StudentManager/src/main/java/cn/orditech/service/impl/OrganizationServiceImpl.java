
package cn.orditech.service.impl;

import cn.orditech.dao.OrganizationDao;
import cn.orditech.dao.impl.OrganizationDaoImpl;
import cn.orditech.service.OrganizationService;
import cn.orditech.dao.impl.BaseDao;
import cn.orditech.entity.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Service
public class OrganizationServiceImpl extends BaseService<Organization> implements OrganizationService{
    @Autowired
    private OrganizationDao organizationDao;

    protected BaseDao<Organization> getDao(){
        return (OrganizationDaoImpl)this.organizationDao;
    }
    
}