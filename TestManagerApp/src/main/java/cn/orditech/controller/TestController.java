package cn.orditech.controller;

import cn.orditech.entity.Question;
import cn.orditech.entity.TestPaper;
import cn.orditech.entity.TestResult;
import cn.orditech.entity.User;
import cn.orditech.enums.AuthorizationTypeEnum;
import cn.orditech.result.JsonResult;
import cn.orditech.service.QuestionService;
import cn.orditech.service.TestPaperService;
import cn.orditech.service.TestResultService;
import cn.orditech.service.UserService;
import cn.orditech.tool.RequestLocal;
import com.alibaba.fastjson.JSON;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kimi on 2017/3/31.
 */
@RequestMapping("/test")
@Controller
public class TestController {
    @Autowired
    private TestResultService testResultService;
    @Autowired
    private TestPaperService testPaperService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;


    @RequestMapping("/startTest")
    public String startTest (@RequestParam("id") Long id, Model model) {
        TestPaper testPaper = testPaperService.selectOne (id);
        JSONArray questions = JSON.parseArray (testPaper.getQuestions ());
        Map<Long, JSONObject> questionsMap = Maps.newLinkedHashMap ();
        for (int i = 0; i < questions.size (); i++) {
            questionsMap.put (questions.getJSONObject (i).getLong ("id"),
                    questions.getJSONObject (i));
        }
        List<Question> questionList = questionService.selectByIds (
                Lists.newArrayList (questionsMap.keySet ()));
        for (Question question : questionList) {
            JSONObject jsonObject = questionsMap.get (question.getId ());
            jsonObject.put ("title", question.getTitle ());
            jsonObject.put ("type", question.getType ());
            jsonObject.put ("options", JSON.parseArray (question.getOptions ()));
        }
        model.addAttribute ("testPaper", testPaper);
        model.addAttribute ("questions", questionsMap.values ());
        return "do_test";
    }

    @RequestMapping("/submitTest")
    @ResponseBody
    public String submitTest (TestResult result) {
        if (result.getTestId () == null) {
            return JSON.toJSONString (JsonResult.failResult ("找不到试卷"));
        }
        TestPaper testPaper = testPaperService.selectOne (result.getTestId ());
        if (testPaper == null) {
            return JSON.toJSONString (JsonResult.failResult ("找不到试卷"));
        }
        JSONArray questionArray = JSONArray.parseArray (testPaper.getQuestions ());
        Map<Long, JSONObject> quesiontMap = new HashMap<Long, JSONObject> ();
        for (int i = 0; i < questionArray.size (); i++) {
            JSONObject json = questionArray.getJSONObject (i);
            quesiontMap.put (json.getLong ("id"), json);
        }

        JSONArray answerArray = JSON.parseArray (result.getAnswer ());
        List<Long> ids = Lists.newArrayList ();
        Map<Long, JSONObject> answerMap = new HashMap<Long, JSONObject> ();
        for (int i = 0; i < answerArray.size (); i++) {
            JSONObject json = answerArray.getJSONObject (i);
            ids.add (answerArray.getJSONObject (i).getLong ("id"));
            answerMap.put (json.getLong ("id"), json);
        }

        List<Question> questionList = questionService.selectByIds (ids);
        int score = 0;
        for (Question question : questionList) {
            boolean right = false;
            JSONObject json = answerMap.get (question.getId ());
            if (json != null) {
                right = question.getAnswer ().equals (json.getString ("answer"));
            }
            if (right) {
                score += quesiontMap.get (question.getId ()).getInteger ("score");
            }
        }

        result.setUserId (RequestLocal.get ().getUserId ());
        result.setScore (score);
        testResultService.insert (result);
        return JSON.toJSONString (JsonResult.successResult (true));
    }

    @RequestMapping("/testResultList")
    public String testResultList (Model model) {
        User user = RequestLocal.get ().getUser ();
        List<User> users = Lists.newArrayList ();
        if(AuthorizationTypeEnum.MANAGER_STAFF.getLevel ().equals (user.getLevel ())){
            users.addAll(userService.selectUserByDepartment(user.getDepartment ()));
        }else if(AuthorizationTypeEnum.ADMINISTRATOR.getLevel ().equals (user.getLevel ())){
            users.addAll(userService.selectList (new User ()));
        }else{
            users.add(user);
        }
        Map<Long,User> userMap = Maps.newHashMap ();
        for(User u:users){
            userMap.put (u.getId (),u);
        }

        List<TestResult> testResultList = testResultService.selectListByUserIds (Lists.newArrayList (userMap.keySet ()));
        if(testResultList.isEmpty ()){
            model.addAttribute ("testResultList", Lists.newArrayList ());
            return "test_result_list";
        }

        List<Long> testPaperIds = Lists.newArrayList ();
        for(TestResult result : testResultList){
            testPaperIds.add(result.getTestId ());
        }
        List<TestPaper> testPaperList = testPaperService.findByIds (testPaperIds);
        Map<Long,TestPaper> paperMap = Maps.newHashMap ();
        for(TestPaper paper : testPaperList){
            paperMap.put (paper.getId(),paper);
        }

        Map<Long, JSONObject> testResultMap = Maps.newHashMap ();
        for (TestResult testResult : testResultList) {
            JSONObject json = new JSONObject ();
            json.put ("id", testResult.getId ());
            json.put ("score", testResult.getScore ());
            testResultMap.put (testResult.getId (), json);
        }

        List<JSONObject> testResultJsonList = Lists.newArrayList ();
        for (TestResult testResult : testResultList) {
            JSONObject json = new JSONObject ();
            json.put("id",testResult.getId());
            json.put("testId",testResult.getTestId ());
            json.put("title",paperMap.get(testResult.getTestId ()).getTitle ());
            json.put("userId",testResult.getUserId());
            json.put("userName",userMap.get(testResult.getUserId ()).getName());
            json.put("score",testResult.getScore ());
            testResultJsonList.add (json);
        }
        model.addAttribute ("testResultList", testResultJsonList);
        return "test_result_list";
    }
}
