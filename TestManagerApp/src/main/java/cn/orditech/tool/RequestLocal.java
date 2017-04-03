package cn.orditech.tool;

import cn.orditech.dao.impl.LoginTicketDaoImpl;
import cn.orditech.entity.LoginTicket;
import cn.orditech.entity.User;
import cn.orditech.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by kimi on 2017/4/3.
 */
@Component
public class RequestLocal implements ApplicationContextAware{
    public static UserService userService;
    public static LoginTicketDaoImpl loginTicketService;

    public static ThreadLocal<RequestLocal> threadLocal = new ThreadLocal<RequestLocal> ();

    public static boolean initThreadLocal(HttpServletRequest request,HttpServletResponse response){
        String ticket = null;
        if(request.getCookies()!=null){
            for(Cookie cookie :request.getCookies()){
                if(cookie.getName().equals("ticket")){
                    ticket=cookie.getValue();
                    break;
                }
            }
        }

        if(ticket != null){
            LoginTicket loginTicket = loginTicketService.getLoginTicketByTicket(ticket);
            if(loginTicket==null || loginTicket.getExpired().before(new Date ()) || loginTicket.getStatus() != 0){
                return false;
            }
            User user = userService.selectOne(loginTicket.getUserId());
            if(user==null){
                return false;
            }
            RequestLocal requestLocal = new RequestLocal();
            requestLocal.setUser (user);
            requestLocal.setUserId (user.getId ());
            requestLocal.setRequest (request);
            requestLocal.setResponse (response);
            RequestLocal.threadLocal.set (requestLocal);
            return true;
        }
        return false;
    }

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Long userId;
    private User user;

    public static void setUserService (UserService userService) {
        RequestLocal.userService = userService;
    }

    public HttpServletRequest getRequest () {
        return request;
    }

    public void setRequest (HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse () {
        return response;
    }

    public void setResponse (HttpServletResponse response) {
        this.response = response;
    }

    public Long getUserId () {
        return userId;
    }

    public void setUserId (Long userId) {
        this.userId = userId;
    }

    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    @Override
    public void setApplicationContext (ApplicationContext applicationContext) throws BeansException {
        RequestLocal.userService = applicationContext.getBean (UserService.class);
        RequestLocal.loginTicketService = applicationContext.getBean (LoginTicketDaoImpl.class);
    }
}
