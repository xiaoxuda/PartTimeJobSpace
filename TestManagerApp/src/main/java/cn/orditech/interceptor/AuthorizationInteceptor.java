package cn.orditech.interceptor;

import cn.orditech.annotation.Authorization;
import cn.orditech.entity.HostHolder;
import cn.orditech.entity.User;
import cn.orditech.enums.AuthorizationTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by kimi on 2017/4/3.
 */
@Component("authorizationInteceptor")
public class AuthorizationInteceptor extends HandlerInterceptorAdapter {
    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Method method = ((HandlerMethod) handler).getMethod ();
        Authorization authorization = method.getAnnotation (Authorization.class);
        if (authorization == null) {
            return true;
        } else {
            AuthorizationTypeEnum typeEnum = authorization.value ();
            /*User user = hostHolder.getUser ();
            if (user.getType () >= typeEnum.getLevel ()) {
                return true;
            }*/
            return true;
        }
    }

}
