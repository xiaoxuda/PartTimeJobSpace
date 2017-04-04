package cn.orditech.configuration;

import cn.orditech.interceptor.AuthorizationInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2017/3/26.
 */
@Component
public class MyConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    private AuthorizationInteceptor authorizationInteceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor (authorizationInteceptor).addPathPatterns ("/")
                .excludePathPatterns ("/login.htm");
        super.addInterceptors(registry);
    }
}
