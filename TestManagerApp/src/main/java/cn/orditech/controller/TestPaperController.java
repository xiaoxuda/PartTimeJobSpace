package cn.orditech.controller;

import cn.orditech.entity.Question;
import cn.orditech.entity.TestPaper;
import cn.orditech.result.JsonResult;
import cn.orditech.service.QuestionService;
import cn.orditech.service.TestPaperService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by kimi on 2017/3/26.
 */
@Controller
@RequestMapping("/testPaper")
public class TestPaperController {
    @Autowired
    private TestPaperService testPaperService;
    @Autowired
    private QuestionService questionService;
    
    @RequestMapping("/testPaperAdd")
    public String testPaperAdd(){
        return "test_paper_edit";
    }

    @RequestMapping("/testPaperEdit")
    public String testPaperEdit(@RequestParam("id") Long id, Model model){
        TestPaper testPaper = testPaperService.selectOne (id);
        JSONArray questions = JSONArray.parseArray (testPaper.getQuestions ());

        //获取试题信息
        Map<Long,JSONObject> quesionMap = Maps.newHashMap ();
        for(int i=0;i<questions.size ();i++){
            JSONObject jsonObject = questions.getJSONObject (i);
            Long questionId = jsonObject.getLong("id");
            quesionMap.put (questionId,jsonObject);
        }
        List<Question> questionList = questionService.selectByIds(Lists.newArrayList (quesionMap.keySet ()));
        for(Question question:questionList){
            JSONObject jsonObject = quesionMap.get (question.getId());
            jsonObject.put("title",question.getTitle ());
        }


        model.addAttribute ("testPaper",testPaper);
        model.addAttribute ("questions",questions);
        model.addAttribute ("isEdit",true);

        return "test_paper_edit";
    }

    @RequestMapping("/testPaperSave")
    @ResponseBody
    public String testPaperSave(TestPaper testPaper){
        JsonResult result;
        try {
            if (testPaper.getId () == null) {
                testPaperService.insert (testPaper);
            } else {
                testPaperService.updateSelective (testPaper);
            }
            result = JsonResult.successResult (null);
        }catch (Exception e){
            result = JsonResult.failResult (e.getMessage ());
        }
        return JSONObject.toJSONString(result);
    }

    @RequestMapping("/testPaperList")
    public String testPaperList(Model model){
        List<TestPaper> testPaperList = testPaperService.selectList (new TestPaper ());
        model.addAttribute ("testPaperList",testPaperList);

        return "test_paper_list";
    }

}