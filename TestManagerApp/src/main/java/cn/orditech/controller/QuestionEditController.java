package cn.orditech.controller;

import cn.orditech.entity.Question;
import cn.orditech.result.JsonResult;
import cn.orditech.service.QuestionService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
    public String questionSave(Question question){
        JsonResult result;
        try {
            if (question.getId () == null) {
                questionService.insert (question);
            } else {
                questionService.updateSelective (question);
            }
            result = JsonResult.successResult (null);
        }catch (Exception e){
            result = JsonResult.failResult (e.getMessage ());
        }
        return JSONObject.toJSONString(result);
    }
}
