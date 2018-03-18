package cn.orditech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kimi on 2017/3/5.
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping("index")
    public String index(HttpServletRequest request,HttpServletResponse respone){
        return "index";
    }
}
