
package cn.orditech.service.impl;

import cn.orditech.dao.UserDao;
import cn.orditech.dao.impl.LoginTicketDaoImpl;
import cn.orditech.dao.impl.UserDaoImpl;
import cn.orditech.entity.LoginTicket;
import cn.orditech.service.UserService;
import cn.orditech.dao.impl.BaseDao;
import cn.orditech.entity.User;
import cn.orditech.tool.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 
 * @author kimi
 * @version 1.0
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginTicketDaoImpl loginTicketDao;

    protected BaseDao<User> getDao(){
        return (UserDaoImpl)this.userDao;
    }

    @Override
    public int insert(User entity){
        return userDao.insert(entity);
    }

    @Override
    public User getUserByAccount(String account){
        return userDao.getUserByAccount(account);
    }

    @Override
    public User getUserByAccountAndPassword(String account,String password){
        return userDao.getUserByAccountAndPassword(account,password);
    }

    @Override
    public Map<String,Object> register(User user){
        Map<String,Object> map = new HashMap<String, Object>();
        if(StringUtils.isBlank(user.getAccount())){
            map.put("msg","用户名不能为空");
            return map;
        }
        if(StringUtils.isBlank(user.getPassword())){
            map.put("msg","密码不能为空");
            return map;
        }
        if(StringUtils.isBlank(user.getName())){
            map.put("msg","姓名不能为空");
            return map;
        }
        User exist = userDao.getUserByAccount(user.getAccount());
        if(exist != null){
            map.put("msg","用户名已存在");
            return map;
        }
        user.setPassword(MD5Utils.MD5(user.getPassword()));//密码使用MD5加密

        userDao.insert(user);
        map.put("msg","注册成功");

        //注册成功以后直接登录，服务器段产生T票并下发到用户浏览器
        String ticket = addLoginTicket(user.getId());
        map.put("ticket",ticket);
        return map;
    }

    @Override
    public Map<String,Object> login(String account,String password){
        Map<String,Object> map = new HashMap<String, Object>();
        if(StringUtils.isBlank(account)){
            map.put("msg","用户名不能为空");
            return map;
        }
        if(StringUtils.isBlank(password)){
            map.put("msg","密码不能为空");
            return map;
        }
        User user = userDao.getUserByAccountAndPassword(account,MD5Utils.MD5(password));
        if(user==null){
            map.put("msg","用户名或密码错误");
            return map;
        }else {
            map.put("msg","登录成功");
        }
        String ticket = addLoginTicket(user.getId());
        map.put("ticket",ticket);
        return map;
    }

    @Override
    public void logout(String ticket,int status){
        loginTicketDao.updateTicketStatus(ticket,status);
    }

    @Override
    public List<User> selectUserByDepartment (String code) {
        return userDao.selectUserByDepartment(code);
    }

    @Override
    public List<Long> selectAllUserIds () {
        return userDao.selectAllUserIds ();
    }

    private String addLoginTicket(Long userId){
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date=new Date();
        date.setTime(date.getTime()+1000*3600*24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
        loginTicketDao.insert(ticket);//把标记用户登录的ticket保存到服务器

        return ticket.getTicket();
    }
}