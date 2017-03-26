package cn.orditech.interceptor;

import cn.orditech.dao.LoginTicketDao;
import cn.orditech.dao.impl.LoginTicketDaoImpl;
import cn.orditech.dao.impl.UserDaoImpl;
import cn.orditech.entity.HostHolder;
import cn.orditech.entity.LoginTicket;
import cn.orditech.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/26.
 */
@Component
public class PassportInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginTicketDaoImpl loginTicketDao;

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket = null;
        if(hostHolder.getUser() != null){//如果当前用户已登录，且hostHolder中保存有当前线程对应的user信息，无需再次从cookie中取T票设置用户信息
            return true;
        }
        if(httpServletRequest.getCookies()!=null){
            for(Cookie cookie :httpServletRequest.getCookies()){
                if(cookie.getName().equals("ticket")){
                    ticket=cookie.getValue();
                    break;
                }
            }
        }

        if(ticket != null){
            LoginTicket loginTicket = loginTicketDao.getLoginTicketByTicket(ticket);
            if(loginTicket==null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() != 0){
                return true;
            }
            User user = userDao.selectOne(loginTicket.getUserId());
            hostHolder.setUser(user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null && hostHolder.getUser() != null){
            modelAndView.addObject("user",hostHolder.getUser());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        hostHolder.clear();
    }
}
