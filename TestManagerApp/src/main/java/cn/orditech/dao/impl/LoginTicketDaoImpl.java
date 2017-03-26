package cn.orditech.dao.impl;

import cn.orditech.dao.LoginTicketDao;
import cn.orditech.entity.LoginTicket;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/26.
 */
@Repository
public class LoginTicketDaoImpl extends BaseDao<LoginTicket> implements LoginTicketDao {
    public LoginTicketDaoImpl() {
        this.namespace="cn.orditech.dao.impl.LoginTicketDaoImpl";
    }

    public LoginTicket getLoginTicketByTicket(String ticket){
        return sqlSession.selectOne(this.namespace+".getLoginTicketByTicket", ticket);
    }

//    @Update()
    public void updateTicketStatus(@Param("ticket")String ticket,@Param("status")int status){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setTicket(ticket);
        loginTicket.setStatus(status);
        sqlSession.update(this.namespace+".updateTicketStatus" ,loginTicket);
    }
}
