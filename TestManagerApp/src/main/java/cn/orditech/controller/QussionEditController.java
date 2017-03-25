package cn.orditech.controller;

import cn.orditech.service.QuessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kimi on 2017/3/25.
 */
@Controller
@RequestMapping("/quession")
public class QussionEditController {
    @Autowired
    private QuessionService quessionService;

    @RequestMapping("/quessionEdit")
    public String quessionAdd(HttpServletRequest request){
        return "quession_edit";
    }
}
