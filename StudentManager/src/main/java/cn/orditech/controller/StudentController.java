package cn.orditech.controller;

import cn.orditech.entity.Student;
import cn.orditech.result.JsonResult;
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
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model){
        return "student_list";
    }

    @RequestMapping("/getStudents")
    @ResponseBody
    public String getStudents(HttpServletRequest request, Student student, Model model){
        List<Student> students = studentService.selectList (student);

        return JsonResult.successResult (students).toString ();
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(HttpServletRequest request, Student student){
        if(student.getId () != null){
            studentService.updateById (student);
        }else{
            studentService.insert (student);
        }
        return JsonResult.successResult (true).toString ();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("studentId") long studentId){
        studentService.delete (studentId);
        scoreService.deleteByStudentId (studentId);
        return JsonResult.successResult (true).toString ();
    }

}
