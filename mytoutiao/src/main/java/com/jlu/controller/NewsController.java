package com.jlu.controller;

import com.jlu.model.*;
import com.jlu.service.CommentService;
import com.jlu.service.NewsService;
import com.jlu.service.QiniuService;
import com.jlu.service.UserService;
import com.jlu.utils.ToutiaoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    private QiniuService qiniuService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @RequestMapping(path = {"/uploadImage/"},method = {RequestMethod.POST})
    @ResponseBody
    public String uploadimage(@RequestParam("file") MultipartFile file){
        try{
//            String fileUrl=newsService.saveImage(file);
            String fileUrl= qiniuService.saveImage(file);
            //file.transferTo();了解下这是做什么的
            if (fileUrl == null){
                return ToutiaoUtil.getJSONString(1,"上传图片失败");
            }
            return ToutiaoUtil.getJSONString(0,fileUrl);
        }catch (Exception e){
            logger.error("上传图片失败"+e.getMessage());
            return ToutiaoUtil.getJSONString(1,"上传图片失败");
        }
    }

    @RequestMapping(path = {"/image"},method = {RequestMethod.GET})
    @ResponseBody
    public void showImage(@RequestParam("name")String filename, HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("image/jpeg");
            StreamUtils.copy(new FileInputStream(new File(ToutiaoUtil.QINIU_DOMAIN_PEFIX  + filename)),response.getOutputStream());
        }catch (Exception e){
            logger.error("图片显示错误："+e.getMessage());
        }

    }

    @RequestMapping(path = {"/user/addNews/"},method = {RequestMethod.POST})
    @ResponseBody
    public String addNews(@RequestParam("image")String image
            ,@RequestParam("title")String title
            ,@RequestParam("link")String link){
        try {
            News news = new News();
            news.setTitle(title);
            news.setLink(link);
            news.setImage(image);
            Date date = new Date();
            news.setCreatedDate(date);
            if (hostHolder.getUser() != null) {
                news.setUserId(hostHolder.getUser().getId());//已登录用户
            } else {
                //匿名用户，设置为固定值
                news.setUserId(00);
            }
            newsService.addNews(news);
            return ToutiaoUtil.getJSONString(0,"add news success");
        }catch (Exception e){
            logger.error("分享资讯错误:",e.getMessage());
            return null;
        }
    }

    @RequestMapping(path = {"/news/{newsId}"},method = {RequestMethod.GET})
    public String newsDetail(@PathVariable("newsId")int newsId,Model model){
        try{
            News news = newsService.getNewsById(newsId);
            List<ViewObject> comments = new ArrayList<ViewObject>();
            if(news !=null){
                List<Comment> commentList = commentService.getCommentsByEntity(news.getId(), EntityType.ENTITY_TYPE_NEWS);
                for(Comment c :commentList){
                    ViewObject vo =new ViewObject();
                    vo.set("comment",c);
                    vo.set("user",userService.getUser(c.getUserId()));
                    comments.add(vo);
                }
            }
            model.addAttribute("news",news);
            model.addAttribute("owner",userService.getUser(news.getUserId()));
            model.addAttribute("comments",comments);
        }catch (Exception e){
            logger.error("资讯详情页错误：",e.getMessage());
        }
        return "detail";
    }

    @RequestMapping(path = {"/addComment"},method = {RequestMethod.POST})
    public String addComment(@RequestParam("newsId")int newsId
            ,@RequestParam("content")String content){
        try{
            Comment comment = new Comment();
            comment.setCreatedDate(new Date());
            comment.setEntityId(newsId);
            comment.setEntityType(EntityType.ENTITY_TYPE_NEWS);
            comment.setContent(content);
            comment.setUserId(hostHolder.getUser().getId());
            commentService.addComment(comment);

            //此处更新评论数要做异步处理
            int count = commentService.getCommentCount(newsId,EntityType.ENTITY_TYPE_NEWS);
            newsService.updateCommentCount(newsId,count);
        }catch (Exception e){
            logger.error("添加评论失败"+e.getMessage());
        }
        return "redirect:/news/"+String.valueOf(newsId);
    }
}
