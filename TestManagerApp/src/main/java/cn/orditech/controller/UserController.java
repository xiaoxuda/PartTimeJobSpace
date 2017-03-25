package cn.orditech.controller;

import cn.orditech.entity.User;
import cn.orditech.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/addUser")
    public String questionAdd(){
        return "user_login";
    }

    @RequestMapping(path = "/register",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String register(User user,
                           @RequestParam(value = "rememberMe",required = false)String rememberMe){
        try{
        if(user.getId() == null){
            userService.insert(user);
        }else {
            userService.update(user);
        }
        return "注册成功";
        }catch (Exception e){
            e.printStackTrace();
            logger.info("注册异常",e.getMessage());
            return "注册失败";
        }
    }
}
