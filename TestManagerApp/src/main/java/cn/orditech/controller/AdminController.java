package cn.orditech.controller;

import cn.orditech.annotation.Authorization;
import cn.orditech.entity.Department;
import cn.orditech.entity.User;
import cn.orditech.enums.AuthorizationTypeEnum;
import cn.orditech.result.JsonResult;
import cn.orditech.service.DepartmentService;
import cn.orditech.service.UserService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by kimi on 2017/4/8.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger (AdminController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;

    @Authorization(AuthorizationTypeEnum.ADMINISTRATOR)
    @RequestMapping("/userList")
    public String userManager(Model model){
        List<User> userList = userService.selectList (new User ());
        List<Department> departments = departmentService.selectList (new Department ());
        Map<String,Department> departmentMap = Maps.newHashMap ();
        for(Department department:departments){
            departmentMap.put(department.getCode (),department);
        }
        model.addAttribute ("userList",userList);
        model.addAttribute ("departmentMap",departmentMap);
        return "user_manager";
    }

    @RequestMapping("/confirmAuth")
    @ResponseBody
    public String confirmAuth(@RequestParam("userId") Long userId){
        User user = userService.selectOne (userId);
        if(user==null){
            return JsonResult.failResult ("查询不到用户信息").toString ();
        }
        user.setLevel (AuthorizationTypeEnum.MANAGER_STAFF.getLevel ());
        userService.update (user);
        return JsonResult.successResult (true).toString ();
    }

    @RequestMapping("/cancelAuth")
    @ResponseBody
    public String cancelAuth(@RequestParam("userId") Long userId){
        User user = userService.selectOne (userId);
        if(user==null){
            return JsonResult.failResult ("查询不到用户信息").toString ();
        }
        user.setLevel (AuthorizationTypeEnum.GENERAL_STAFF.getLevel ());
        userService.update (user);
        return JsonResult.successResult (true).toString ();
    }
}
