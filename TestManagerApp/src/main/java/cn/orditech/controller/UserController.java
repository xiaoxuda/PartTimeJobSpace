package cn.orditech.controller;

import cn.orditech.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/3/25.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(path = "/register",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String register(@RequestParam(value = "account",required = false)String account,
                           @RequestParam(value = "password",required = false)String password,
                           HttpResponse response){

        return "";
    }
}
