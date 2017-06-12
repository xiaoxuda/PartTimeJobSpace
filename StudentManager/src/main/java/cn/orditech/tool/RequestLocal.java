package cn.orditech.tool;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kimi on 2017/4/3.
 */
@Component
public class RequestLocal implements ApplicationContextAware{

    private static ThreadLocal<RequestLocal> threadLocal = new ThreadLocal<RequestLocal> ();

    public static boolean initThreadLocal(HttpServletRequest request,HttpServletResponse response){
        //businness logic
        return true;
    }

    public static RequestLocal get(){
        RequestLocal requestLocal = threadLocal.get ();
        return requestLocal==null?new RequestLocal ():requestLocal;
    }

    public static void clear(){
        threadLocal.remove ();
    }

    private HttpServletRequest request;
    private HttpServletResponse response;
    //other bussinss attribute

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

    @Override
    public void setApplicationContext (ApplicationContext applicationContext) throws BeansException {
    }
}
