package cn.orditech.controller;

import cn.orditech.entity.User;
import cn.orditech.service.UserService;
import cn.orditech.tool.MyUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/25.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/addUser")
    public String addUser(Model model){
        model.addAttribute("loginOrRegister","0");
        return "user_register";
    }

    @RequestMapping(path = "/register",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String register(User user,
                           @RequestParam(value = "rememberMe",required = false)String rememberMe,
                           Model model,
                           HttpServletResponse response){
        try{
            Map<String,Object> map = userService.register(user);
            //服务器端注册成功后，下发T票
            if(StringUtils.equals((String) map.get("msg"),"注册成功")){
                if(map.containsKey("ticket")){
                    Cookie cookie = new Cookie("ticket",map.get("ticket").toString());
                    cookie.setPath("/");
                    if(StringUtils.equals(rememberMe,"on")){
                        cookie.setMaxAge(3600*24*3);//cookie有效时间
                    }
                    response.addCookie(cookie);
                }
                return MyUtil.getJSONString(0,(String) map.get("msg"));
            }else {
                return MyUtil.getJSONString(1,map);
            }

        }catch (Exception e){
            e.printStackTrace();
            logger.info("注册异常",e.getMessage());
            return MyUtil.getJSONString(1,"注册异常");
        }
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("loginOrRegister","1");
        return "user_register";
    }

    @RequestMapping(path = "/checkLogin",method = {RequestMethod.GET,RequestMethod.POST})
    public String checkLogin(@RequestParam(value = "account",required = false)String account,
                        @RequestParam(value = "password",required = false)String password,
                        @RequestParam(value = "rememberMe",required = false)String rememberMe,
                        HttpServletResponse response,
                        Model model){
        try{
            Map<String,Object> map = userService.login(account, password);
            if(map.containsKey("msg")){
                if(map.containsKey("ticket")){
                    Cookie cookie = new Cookie("ticket",(String) map.get("ticket"));
                    cookie.setPath("/");
                    if(StringUtils.equals("on",rememberMe)){
                        cookie.setMaxAge(3600*24*3);
                    }
                    response.addCookie(cookie);
                }
                return MyUtil.getJSONString(0,(String) map.get("msg"));
            }else {
                return MyUtil.getJSONString(1,map);
            }
        }catch (Exception e){
            logger.info("登录异常",e.getMessage());
            return MyUtil.getJSONString(1,"登录异常");
        }
    }

    @RequestMapping(path = "/logout",method = {RequestMethod.GET,RequestMethod.POST})
    public String logout(@CookieValue("ticket")String ticket){
        userService.logout(ticket,1);
        return "redirect:/index.htm";
    }
}
