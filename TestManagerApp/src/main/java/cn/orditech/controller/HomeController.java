package cn.orditech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kimi on 2017/3/5.
 */
@Controller
@Service
@RequestMapping("/")
public class HomeController {
    @Autowired
    @RequestMapping("index")
    public String index(HttpServletRequest request,HttpServletResponse respone){
        return "index";
    }
}
