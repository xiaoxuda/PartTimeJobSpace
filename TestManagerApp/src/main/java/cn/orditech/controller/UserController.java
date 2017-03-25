package cn.orditech.controller;

import cn.orditech.entity.User;
import cn.orditech.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/3/25.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(path = "/register",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String register(@RequestParam(value = "account",required = false)String account,
                           @RequestParam(value = "password",required = false)String password,
                           @RequestParam(value = "name",required = false)String name,
                           HttpServletResponse response){
        try{
        User user = new User();
        user.setAcount(account);
        user.setPassword(password);
        user.setName(name);
        userService.insert(user);
        return "注册成功";
        }catch (Exception e){
            logger.info("注册异常",e.getMessage());
            return "注册失败";
        }
    }
}
