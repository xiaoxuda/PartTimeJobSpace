package cn.orditech.controller;

import cn.orditech.entity.Department;
import cn.orditech.entity.User;
import cn.orditech.enums.AuthorizationTypeEnum;
import cn.orditech.enums.UserTypeEnum;
import cn.orditech.result.JsonResult;
import cn.orditech.service.DepartmentService;
import cn.orditech.service.UserService;
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
    private static final Logger logger = LoggerFactory.getLogger (UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/register")
    public String addUser (Model model) {
        List<Department> departments = departmentService.selectList (new Department ());

        model.addAttribute ("departments", departments);
        return "user_register";
    }

    @RequestMapping(path = "/doRegister", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String register (User user,HttpServletResponse response) {
        try {
            user.setLevel (AuthorizationTypeEnum.GENERAL_STAFF.getLevel ());
            user.setType (UserTypeEnum.GENERAL.getType ());
            Map<String, Object> map = userService.register (user);
            //服务器端注册成功后，下发T票
            if (map.containsKey ("ticket")) {
                Cookie cookie = new Cookie ("ticket", map.get ("ticket").toString ());
                cookie.setPath ("/");
                response.addCookie (cookie);
                return JsonResult.successResult (true).toString ();
            } else {
                return JsonResult.failResult ((String) map.get ("msg")).toString ();
            }

        } catch (Exception e) {
            e.printStackTrace ();
            logger.info ("注册异常", e.getMessage ());
            return JsonResult.failResult (e.getMessage ()).toString ();
        }
    }

    @RequestMapping("/login")
    public String login (Model model) {
        return "user_login";
    }

    @RequestMapping(path = "/doLogin", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String checkLogin (@RequestParam(value = "account", required = false) String account,
                              @RequestParam(value = "password", required = false) String password,
                              @RequestParam(value = "rememberMe", required = false) String rememberMe,
                              HttpServletResponse response) {
        try {
            Map<String, Object> map = userService.login (account, password);
            if (map.containsKey ("ticket")) {
                Cookie cookie = new Cookie ("ticket", (String) map.get ("ticket"));
                cookie.setPath ("/");
                if (StringUtils.equals ("on", rememberMe)) {
                    cookie.setMaxAge (3600 * 24 * 3);
                }
                response.addCookie (cookie);

                return JsonResult.successResult (true).toString ();
            } else {
                return JsonResult.failResult ((String) map.get ("msg")).toString ();
            }
        } catch (Exception e) {
            logger.info ("登录异常", e.getMessage ());
            return JsonResult.failResult (e.getMessage ()).toString ();
        }
    }

    @RequestMapping(path = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout (@CookieValue("ticket") String ticket) {
        userService.logout (ticket, 1);
        return "user_login";
    }
}
