package cn.orditech.controller;

import cn.orditech.annotation.Authorization;
import cn.orditech.entity.Question;
import cn.orditech.entity.TestPaper;
import cn.orditech.enums.AuthorizationTypeEnum;
import cn.orditech.query.QuestionPageQuery;
import cn.orditech.result.JsonResult;
import cn.orditech.service.QuestionService;
import cn.orditech.service.TestPaperService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kimi on 2017/3/25.
 */
@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Authorization(AuthorizationTypeEnum.ADMINISTRATOR)
    @RequestMapping("/questionEdit")
    public String questionEdit(@RequestParam(value = "id",required = false) Long id, Model model){
        if(id==null){
            return "question_edit";
        }
        Question question = questionService.selectOne (id);
        model.addAttribute ("question",question);
        model.addAttribute ("isEdit",true);

        return "question_edit";
    }

    @Authorization(AuthorizationTypeEnum.ADMINISTRATOR)
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

    @Authorization(AuthorizationTypeEnum.ADMINISTRATOR)
    @RequestMapping("/questionList")
    public String questionList(Model model){
        List<Question> questionList = questionService.selectList (new Question ());
        model.addAttribute ("questionList",questionList);

        return "question_list";
    }

    @Authorization(AuthorizationTypeEnum.ADMINISTRATOR)
    @RequestMapping("/pageQuery")
    @ResponseBody
    public String pageQuery(QuestionPageQuery questionPageQuery){
        List<Question> questionList = questionService.pageQuery (questionPageQuery);
        return JSONArray.toJSONString (JsonResult.successResult (questionList));
    }
}
