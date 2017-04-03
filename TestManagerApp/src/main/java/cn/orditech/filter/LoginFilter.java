package cn.orditech.filter;

import cn.orditech.entity.HostHolder;
import cn.orditech.service.UserService;
import cn.orditech.tool.RequestLocal;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kimi on 2017/4/3.
 */
public class LoginFilter implements Filter {

    @Override
    public void init (FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*boolean result = RequestLocal.initThreadLocal ((HttpServletRequest)request, (HttpServletResponse) response);
        if(result) {
            chain.doFilter (request, response);
        }*/
        chain.doFilter (request, response);
    }

    @Override
    public void destroy () {

    }
}
