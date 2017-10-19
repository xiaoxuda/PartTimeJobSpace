package cn.orditech.controller;

import cn.orditech.entity.Course;
import cn.orditech.entity.Score;
import cn.orditech.entity.Student;
import cn.orditech.result.JsonResult;
import cn.orditech.service.CourseService;
import cn.orditech.service.ScoreService;
import cn.orditech.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by kimi on 2017/6/11.
 */
@Controller
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courceService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, long studentId, Model model){
        model.addAttribute ("courses", courceService.selectList (new Course ()));
        model.addAttribute ("student",studentService.selectOne (studentId));
        return "score_list";
    }

    @RequestMapping("/getScores")
    @ResponseBody
    public String getScores(HttpServletRequest request, Score score, Model model){
        List<Score> scores = scoreService.selectList (score);

        return JsonResult.successResult (scores).toString ();
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(HttpServletRequest request, Score score){
        if(score.getPerfectScore () == null){
            score.setPerfectScore (100);
        }
        if(score.getId () != null){
            scoreService.updateById (score);
        }else{
            scoreService.insert (score);
        }
        return JsonResult.successResult (true).toString ();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("id") long id){
        scoreService.delete (id);
        return JsonResult.successResult (true).toString ();
    }

    @RequestMapping("/curve")
    public String scoreCurveIndex(@RequestParam("studentId") long id, Model model){
        model.addAttribute ("courses", courceService.selectList (new Course ()));
        model.addAttribute ("student",studentService.selectOne (id));
        return "score_curve";
    }
}
