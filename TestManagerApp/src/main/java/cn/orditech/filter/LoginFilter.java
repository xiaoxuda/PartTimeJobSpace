package cn.orditech.filter;

import cn.orditech.tool.RequestLocal;
import com.google.common.collect.Sets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by kimi on 2017/4/3.
 */
public class LoginFilter implements Filter {
    private static Set<String> excludeUrls = Sets.newHashSet (
            "/","/doRegister.htm","/register.htm","/login.htm","/doLogin.htm");

    @Override
    public void init (FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(excludeUrls.contains (((HttpServletRequest)request).getRequestURI ())){
            chain.doFilter (request, response);
            return;
        }
        boolean result = RequestLocal.initThreadLocal ((HttpServletRequest)request, (HttpServletResponse) response);
        if(result) {
            chain.doFilter (request, response);
        }else{
            ((HttpServletResponse) response).sendRedirect ("/login.htm");
        }
    }

    @Override
    public void destroy () {

    }
}
