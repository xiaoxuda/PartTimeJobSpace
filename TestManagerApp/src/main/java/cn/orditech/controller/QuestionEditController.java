package cn.orditech.controller;

import cn.orditech.entity.Question;
import cn.orditech.result.JsonResult;
import cn.orditech.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kimi on 2017/3/25.
 */
@Controller
@RequestMapping("/question")
public class QuestionEditController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/questionAdd")
    public String questionAdd(){
        return "question_edit";
    }

    @RequestMapping("/questionEdit")
    public String questionEdit(@RequestParam("id") Long id, Model model){
        Question question = questionService.selectOne (id);
        model.addAttribute ("question",question);
        model.addAttribute ("isEdit",true);

        return "question_edit";
    }

    @RequestMapping("/questionSave")
    @ResponseBody
    public JsonResult questionSave(Question question){
        try {
            if (question.getId () == null) {
                questionService.insert (question);
            } else {
                questionService.updateSelective (question);
            }
        }catch (Exception e){
            return JsonResult.failResult (e.getMessage ());
        }
        return JsonResult.successResult (null);
    }
}
